package com.emazon.transaction.domain.usecases;

import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.ports.in.SupplyUseCases;
import com.emazon.transaction.domain.ports.out.ArticleServicePort;
import com.emazon.transaction.domain.ports.out.SupplyPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SupplyUseCasesImpl implements SupplyUseCases {

    private final SupplyPersistencePort supplyPersistencePort;
    private final ArticleServicePort articleServicePort;
    @Override
    public List<Supply> listSupply() {
        return null;
    }

    @Override
    public void createSupply(Supply supply) {
        supplyPersistencePort.saveSupply(supply);
        articleServicePort.updateArticle(supply.getArticleId(), supply.getQuantity());
    }


}
