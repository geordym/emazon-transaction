package com.emazon.transaction.infraestructure.client;


import com.emazon.transaction.infraestructure.client.dto.ArticleResponseDto;
import com.emazon.transaction.infraestructure.client.dto.PurchaseDto;
import com.emazon.transaction.infraestructure.configuration.feign.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "REPORT-API", url = "${external.report.api.base-url}", configuration = FeignClientConfig.class)
public interface ReportFeignClient {

    @PostMapping(value = "/api/reports/sale", consumes = MediaType.APPLICATION_JSON_VALUE)
    String createReportSale(@RequestBody PurchaseDto purchaseDto);

}
