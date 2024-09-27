package com.emazon.transaction.domain.ports.out.external;

import com.emazon.transaction.domain.model.dto.shop.ArticleShoppingCartDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportConnectionPort {
    void communicateSaleToReport(String customerEmail, LocalDateTime dateBuy, Double totalSale, List<ArticleShoppingCartDto> articleShoppingCartDtos);

}
