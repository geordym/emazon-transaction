package com.emazon.transaction.domain.usecases;

import com.emazon.transaction.domain.enums.DeliveryStatus;
import com.emazon.transaction.domain.enums.SyncStatus;
import com.emazon.transaction.domain.exception.util.ArrivingSupplyNotFound;
import com.emazon.transaction.domain.exception.util.ArticleConnectionFailure;
import com.emazon.transaction.domain.exception.util.ArticleNotFoundException;
import com.emazon.transaction.domain.exception.util.SupplyNotFoundException;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.ports.in.SupplyUseCases;
import com.emazon.transaction.domain.ports.out.SupplyPersistencePort;
import com.emazon.transaction.domain.ports.out.auth.UserAuthenticationPort;
import com.emazon.transaction.domain.ports.out.external.StockConnectionPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SupplyUseCasesImpl implements SupplyUseCases {

    private final SupplyPersistencePort supplyPersistencePort;
    private final StockConnectionPort stockConnectionPort;
    private final UserAuthenticationPort userAuthenticationPort;



    @Override
    public List<Supply> listSupply() {
        return null;
    }

    @Override
    public void createSupply(Supply supply, boolean isReceived) {

        Long warehouseId = userAuthenticationPort.getAuthenticatedWarehouseId();
        supply.setCreatedByAuxiliaryId(warehouseId);
        try{
            boolean existsArticle = stockConnectionPort.existsArticle(supply.getArticleId());
            if(!existsArticle){
             throw new ArticleNotFoundException();
            }
        }catch (ArticleConnectionFailure a){

        }

        if(isReceived){
            supply.setDeliveryStatus(DeliveryStatus.DELIVERED.getDescription());
            supply.setSyncStatus(SyncStatus.PENDING.getDescription());
            supply.setSupplyDate(LocalDate.now());
            supply.setReceivedAt(LocalDate.now());
        }else{
            supply.setDeliveryStatus(DeliveryStatus.IN_TRANSIT.getDescription());
            supply.setSyncStatus(SyncStatus.WAITING_FOR_RECEIPT.getDescription());
            supply.setSupplyDate(supply.getSupplyDate());
        }

        supply.setCreatedDate(LocalDateTime.now());
        Supply supplySaved = supplyPersistencePort.saveSupply(supply);

        if(isReceived){
            stockConnectionPort.updateArticle(supplySaved.getId(), supply.getArticleId(), supply.getQuantity());
        }

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

    @Override
    public Supply getUpcomingSupplyForArticle(Long articleId) {
        Optional<Supply> supplyOptional = supplyPersistencePort.findClosestInTransitSupplyByArticleId(articleId);
        if(supplyOptional.isEmpty()){
            throw new ArrivingSupplyNotFound();
        }

        return supplyOptional.get();
    }


}
