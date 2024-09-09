package com.emazon.transaction.infraestructure.schedule;


import com.emazon.transaction.domain.enums.SupplyStatus;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.ports.out.ArticleServicePort;
import com.emazon.transaction.domain.ports.out.SupplyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class SupplyRetrySchedulerService implements CommandLineRunner {

    private final TaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledFuture;
    private final ArticleServicePort articleServicePort;
    private final SupplyPersistencePort supplyPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(SupplyRetrySchedulerService.class);


    private final String CRON_DURATION = "0 * * * * *";

    public SupplyRetrySchedulerService(@Lazy final ArticleServicePort articleServicePort,
                                       @Lazy final SupplyPersistencePort supplyPersistencePort) {
        this.taskScheduler = new ConcurrentTaskScheduler();
        this.articleServicePort = articleServicePort;
        this.supplyPersistencePort = supplyPersistencePort;
    }

    public void startRetryTask() {
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            scheduledFuture = taskScheduler.schedule(this::retryFailedSupplies, new CronTrigger(CRON_DURATION));
            logger.info("Scheduler iniciado...");
        }
    }

    public void stopRetryTask() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(false);
            logger.info("Scheduler detenido");
        } else {
            logger.info("No hay Scheduler en ejecución.");
        }
    }

    private void retryFailedSupplies() {
        logger.info("Reintentando procesar suministros fallidos...");
        List<Supply> suppliesPendingToSend = supplyPersistencePort.getPendingSupplies(SupplyStatus.PENDING.getDisplayName());
        if (suppliesPendingToSend.isEmpty()) {
            logger.info("No hay suministros para procesar, cancelando hilo");
            this.stopRetryTask();
            return;
        }

        suppliesPendingToSend.forEach(supply -> {
            logger.info("Reintentando procesar el suministro id: {}", supply.getId());
            articleServicePort.updateArticle(supply.getId(), supply.getArticleId(), supply.getQuantity());
        });
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("System started, verifying supplies with status PENDING");
        List<Supply> suppliesPendingToSend = supplyPersistencePort.getPendingSupplies(SupplyStatus.PENDING.getDisplayName());
        if (!suppliesPendingToSend.isEmpty()) {
            logger.info("Supplies pending to send has been founded, starting schedule...");
            this.startRetryTask();
        }else{
            logger.info("Dont have supplies pending to send");
        }
    }

}