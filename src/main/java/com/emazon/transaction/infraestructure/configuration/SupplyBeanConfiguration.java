package com.emazon.transaction.infraestructure.configuration;


import com.emazon.transaction.application.services.implementations.SupplyServiceImpl;
import com.emazon.transaction.application.services.interfaces.ISupplyService;
import com.emazon.transaction.domain.ports.in.SupplyUseCases;
import com.emazon.transaction.domain.ports.out.SupplyPersistencePort;
import com.emazon.transaction.domain.usecases.SupplyUseCasesImpl;
import com.emazon.transaction.infraestructure.adapter.SupplyPersistenceMysqlAdapter;
import com.emazon.transaction.infraestructure.repository.SupplyCrudRepositoryMySql;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupplyBeanConfiguration {

    @Bean
    public ISupplyService supplyService(SupplyUseCases supplyUseCases){
        return new SupplyServiceImpl(supplyUseCases);
    }

    @Bean
    public SupplyUseCases supplyUseCases(SupplyPersistencePort supplyPersistencePort){
        return new SupplyUseCasesImpl(supplyPersistencePort);
    }

    @Bean
    public SupplyPersistencePort supplyPersistencePort(SupplyCrudRepositoryMySql crudRepositoryMySql){
        return new SupplyPersistenceMysqlAdapter(crudRepositoryMySql);
    }

}
