package com.emazon.transaction.infraestructure.adapter;

import com.emazon.transaction.domain.model.dto.shop.ArticleShoppingCartDto;
import com.emazon.transaction.domain.ports.out.external.ReportConnectionPort;
import com.emazon.transaction.infraestructure.client.ReportFeignClient;
import com.emazon.transaction.infraestructure.client.dto.ArticleReportDto;
import com.emazon.transaction.infraestructure.client.dto.PurchaseDto;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RequiredArgsConstructor
public class ReportConnectionFeignAdapter implements ReportConnectionPort {

    private final ReportFeignClient reportFeignClient;
    @Override
    public void communicateSaleToReport(String customerEmail, LocalDateTime dateBuy, Double totalSale, List<ArticleShoppingCartDto> articleShoppingCartDtos) {
        String isoString = dateBuy.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        List<ArticleReportDto> articleReportDto = articleShoppingCartDtos.stream().map(articleShoppingCartDto -> {
            return articleDomainToArticleReportDto(articleShoppingCartDto);
        }).toList();

        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setEmailCustomer(customerEmail);
        purchaseDto.setDatetimeBuy(isoString);
        purchaseDto.setTotalCost(totalSale);
        purchaseDto.setEmailCustomer(customerEmail);
        purchaseDto.setArticleList(articleReportDto);

        reportFeignClient.createReportSale(purchaseDto);
    }


    public ArticleReportDto articleDomainToArticleReportDto(ArticleShoppingCartDto articleShoppingCartDto){
        ArticleReportDto articleReportDto = new ArticleReportDto();
        articleReportDto.setId(articleShoppingCartDto.getId());
        articleReportDto.setName(articleShoppingCartDto.getName());
        articleReportDto.setQuantity(articleShoppingCartDto.getQuantityInCart());
        articleReportDto.setPrice(articleShoppingCartDto.getUnitPrice());
        articleReportDto.setTotal(articleShoppingCartDto.getUnitPrice() * articleShoppingCartDto.getQuantityInCart());
        return articleReportDto;
    }
}
