package com.emazon.transaction.domain.ports.in;

import com.emazon.transaction.domain.model.Supply;

import java.util.List;
import java.util.Optional;

public interface SupplyUseCases {

    List<Supply> listSupply();
    void createSupply(Supply supply, boolean isReceived);

    void processReceivedSupply(Long supplyId);

    void processRejectedSupply(Long supplyId);

    Supply getUpcomingSupplyForArticle(Long articleId);


}
