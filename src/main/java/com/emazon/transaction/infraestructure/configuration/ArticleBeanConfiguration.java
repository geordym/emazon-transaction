package com.emazon.transaction.infraestructure.configuration;

import com.emazon.transaction.domain.ports.out.ArticleServicePort;
import com.emazon.transaction.infraestructure.adapter.ArticleServiceFeignAdapter;
import com.emazon.transaction.infraestructure.client.ArticleFeignClient;
import com.emazon.transaction.infraestructure.schedule.SupplyRetrySchedulerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleBeanConfiguration {

    @Bean
    public ArticleServicePort articleServicePort(ArticleFeignClient articleFeignClient, SupplyRetrySchedulerService supplyRetrySchedulerService){
        return new ArticleServiceFeignAdapter(articleFeignClient, supplyRetrySchedulerService);
    }

}
