package com.emazon.transaction.application.handler;

import com.emazon.transaction.application.dto.rest.response.SupplyResponseDto;

public interface ISupplyRestHandler {

    SupplyResponseDto getUpcomingSupplyForArticle(Long articleId);

}
