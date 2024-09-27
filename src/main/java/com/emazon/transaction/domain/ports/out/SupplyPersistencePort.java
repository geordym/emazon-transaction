package com.emazon.transaction.domain.ports.out;

import com.emazon.transaction.domain.model.Supply;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SupplyPersistencePort {

    Supply saveSupply(Supply supply);

    List<Supply> getSyncPendingSupplies(String status);

    void updateSupplySyncStatusToCompleted(Long supplyId);
    void updateSupplySyncStatusToRejected(Long supplyId);

    Optional<Supply> findSupplyById(Long supplyId);

    Optional<Supply> findClosestInTransitSupplyByArticleId(Long articleId);


}
