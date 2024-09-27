package com.emazon.transaction.infraestructure.configuration;

import com.emazon.transaction.domain.ports.out.external.StockConnectionPort;
import com.emazon.transaction.infraestructure.adapter.StockConnectionFeignAdapter;
import com.emazon.transaction.infraestructure.client.StockFeignClient;
import com.emazon.transaction.infraestructure.schedule.SupplyRetrySchedulerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockBeanConfiguration {

    @Bean
    public StockConnectionPort stockConnectionPort(StockFeignClient stockFeignClient, SupplyRetrySchedulerService supplyRetrySchedulerService){
        return new StockConnectionFeignAdapter(stockFeignClient, supplyRetrySchedulerService);
    }


}
