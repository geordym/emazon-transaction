package com.emazon.transaction.infraestructure.schedule;


import com.emazon.transaction.domain.enums.SupplyStatus;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.ports.out.ArticleServicePort;
import com.emazon.transaction.domain.ports.out.SupplyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
public class SupplyRetrySchedulerService {

    private final TaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledFuture;
    private final ArticleServicePort articleServicePort;
    private final SupplyPersistencePort supplyPersistencePort;

    public SupplyRetrySchedulerService(@Lazy final ArticleServicePort articleServicePort,
                                       @Lazy final SupplyPersistencePort supplyPersistencePort) {
        this.taskScheduler = new ConcurrentTaskScheduler();
        this.articleServicePort = articleServicePort;
        this.supplyPersistencePort = supplyPersistencePort;
    }

    public void startRetryTask() {
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            scheduledFuture = taskScheduler.schedule(this::retryFailedSupplies, new CronTrigger("0 * * * * *"));
            System.out.println("Scheduler iniciado");
        } else {
           // System.out.println("El Scheduler ya está en ejecución.");
        }
    }

    // Método para detener el scheduler
    public void stopRetryTask() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(false);
            System.out.println("Scheduler detenido");
        } else {
            System.out.println("No hay Scheduler en ejecución.");
        }
    }

    private void retryFailedSupplies() {
        System.out.println("Reintentando procesar suministros fallidos...");
        List<Supply> suppliesPendingToSend = supplyPersistencePort.getPendingSupplies(SupplyStatus.PENDING.getDisplayName());
        if(suppliesPendingToSend.size() == 0){
            System.out.println("Dont have supplies to process, canceling thread");
            this.stopRetryTask();
        }

        suppliesPendingToSend.stream().forEach(supply -> {
            System.out.println("Retrying process the supply id: " + supply.getId());
            articleServicePort.updateArticle(supply.getId(), supply.getArticleId(), supply.getQuantity());
        });
    }

}