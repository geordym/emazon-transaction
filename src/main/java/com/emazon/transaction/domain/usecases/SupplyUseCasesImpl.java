package com.emazon.transaction.domain.usecases;

import com.emazon.transaction.domain.enums.DeliveryStatus;
import com.emazon.transaction.domain.enums.SyncStatus;
import com.emazon.transaction.domain.exception.util.ArticleConnectionFailure;
import com.emazon.transaction.domain.exception.util.ArticleNotFoundException;
import com.emazon.transaction.domain.exception.util.SupplyNotFoundException;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.ports.in.SupplyUseCases;
import com.emazon.transaction.domain.ports.out.ArticleServicePort;
import com.emazon.transaction.domain.ports.out.SupplyPersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

        try{
            boolean existsArticle = articleServicePort.existsArticle(supply.getArticleId());
            if(!existsArticle){
             throw new ArticleNotFoundException();
            }
        }catch (ArticleConnectionFailure a){

        }

        supply.setDeliveryStatus(DeliveryStatus.IN_TRANSIT.getDescription());
        supply.setSyncStatus(SyncStatus.PENDING.getDescription());
        supply.setCreatedDate(LocalDateTime.now());
        Supply supplySaved = supplyPersistencePort.saveSupply(supply);
        articleServicePort.updateArticle(supplySaved.getId(), supply.getArticleId(), supply.getQuantity());
    }

    @Override
    public void processReceivedSupply(Long supplyId) {
        Optional<Supply> supplyOptional = supplyPersistencePort.findSupplyById(supplyId);
        if(supplyOptional.isEmpty()){
            throw new SupplyNotFoundException();
        }

        supplyPersistencePort.updateSupplySyncStatusToCompleted(supplyId);
    }

    @Override
    public void processRejectedSupply(Long supplyId) {
        Optional<Supply> supplyOptional = supplyPersistencePort.findSupplyById(supplyId);
        if(supplyOptional.isEmpty()){
            throw new SupplyNotFoundException();
        }

        supplyPersistencePort.updateSupplySyncStatusToRejected(supplyId);
    }


}
