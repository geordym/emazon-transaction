package com.emazon.transaction.infraestructure.configuration;

import com.emazon.transaction.application.handler.IShopRestHandler;
import com.emazon.transaction.application.handler.implementations.ShopRestHandler;
import com.emazon.transaction.domain.ports.in.ShopUseCases;
import com.emazon.transaction.domain.ports.out.auth.UserAuthenticationPort;
import com.emazon.transaction.domain.ports.out.external.ReportConnectionPort;
import com.emazon.transaction.domain.ports.out.external.ShoppingCartConnectionPort;
import com.emazon.transaction.domain.ports.out.external.StockConnectionPort;
import com.emazon.transaction.domain.usecases.ShopUsesCasesImpl;
import com.emazon.transaction.infraestructure.adapter.ShoppingCartConnectionFeignAdapter;
import com.emazon.transaction.infraestructure.client.ShoppingCartFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopBeanConfiguration {

    @Bean
    public IShopRestHandler shopRestHandler(final ShopUseCases shopUseCases){
        return new ShopRestHandler(shopUseCases);
    }
    @Bean
    public ShopUseCases shopUseCases(UserAuthenticationPort userAuthenticationPort, ShoppingCartConnectionPort shoppingCartConnectionPort,
                                     StockConnectionPort stockConnectionPort,
                                     ReportConnectionPort reportConnectionPort){
        return new ShopUsesCasesImpl(userAuthenticationPort,
                shoppingCartConnectionPort,
                stockConnectionPort,
                reportConnectionPort
                );
    }

    @Bean
    public ShoppingCartConnectionPort shoppingCartConnectionPort(ShoppingCartFeignClient shoppingCartFeignClient){
        return new ShoppingCartConnectionFeignAdapter(shoppingCartFeignClient);
    }
}
