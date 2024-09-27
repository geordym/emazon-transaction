package com.emazon.transaction.domain.usecases;

import com.emazon.transaction.domain.exception.util.ArticleWithoutStockException;
import com.emazon.transaction.domain.exception.util.ConnectionExternalException;
import com.emazon.transaction.domain.exception.util.ShoppingCartEmpty;
import com.emazon.transaction.domain.model.dto.shop.ArticleShoppingCartDto;
import com.emazon.transaction.domain.model.dto.shop.PaginationArticle;
import com.emazon.transaction.domain.ports.in.ShopUseCases;
import com.emazon.transaction.domain.ports.out.auth.UserAuthenticationPort;
import com.emazon.transaction.domain.ports.out.external.ReportConnectionPort;
import com.emazon.transaction.domain.ports.out.external.ShoppingCartConnectionPort;
import com.emazon.transaction.domain.ports.out.external.StockConnectionPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ShopUsesCasesImpl implements ShopUseCases {

    public static final int ZERO = 0;
    private final UserAuthenticationPort userAuthenticationPort;
    private final ShoppingCartConnectionPort shoppingCartConnectionPort;
    private final StockConnectionPort stockConnectionPort;
    private final ReportConnectionPort reportConnectionPort;

    @Override
    public void buyItemsInCart() {
        Long customerId = userAuthenticationPort.getAuthenticatedCustomerId();
        String token = userAuthenticationPort.getTokenOfAuthenticatedUser();
        PaginationArticle articleList = shoppingCartConnectionPort.getArticlesOfShoppingCartByCustomer(token);

        if(articleList.getTotalElements() == ZERO){
            throw new ShoppingCartEmpty();
        }

        try{
            decreaseArticlesStock(articleList.getContent());
        }catch (ArticleWithoutStockException articleWithoutStockException){
            throw new ArticleWithoutStockException();
        }catch (ConnectionExternalException ex){
            throw new ConnectionExternalException();
        }

        shoppingCartConnectionPort.deleteAllItemsOfShoppingCart(customerId);
        sendSaleReport(articleList.getContent());
    }

    private void sendSaleReport(List<ArticleShoppingCartDto> articleShoppingCartDto){
        Map<Long, Integer> articles = articleShoppingCartDto.stream()
                .collect(Collectors.toMap(
                        ArticleShoppingCartDto::getId,
                        ArticleShoppingCartDto::getQuantityInCart,
                        (existing, replacement) -> existing + replacement
                ));

        String email = userAuthenticationPort.getEmailofAuthenticatedUser();
        LocalDateTime dateBuy = LocalDateTime.now();
        Double totalSale = stockConnectionPort.calculateTotalOfArticles(articles);
        reportConnectionPort.communicateSaleToReport(email, dateBuy, totalSale, articleShoppingCartDto);
    }

    private void decreaseArticlesStock(List<ArticleShoppingCartDto> articleShoppingCartDtos) {
        Map<Long, Integer> articlesToDecrease = articleShoppingCartDtos.stream()
                .collect(Collectors.toMap(
                        ArticleShoppingCartDto::getId,
                        ArticleShoppingCartDto::getQuantityInCart
                ));
        stockConnectionPort.communicateDecreaseOfArticles(articlesToDecrease);
    }

}
