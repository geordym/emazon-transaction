package com.emazon.transaction.infraestructure.configuration;


import com.emazon.transaction.domain.ports.out.external.ReportConnectionPort;
import com.emazon.transaction.infraestructure.adapter.ReportConnectionFeignAdapter;
import com.emazon.transaction.infraestructure.client.ReportFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportBeanConfiguration {

    @Bean
    public ReportConnectionPort reportConnectionPort(ReportFeignClient reportFeignClient){
        return new ReportConnectionFeignAdapter(reportFeignClient);
    }

}
