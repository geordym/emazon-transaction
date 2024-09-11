package com.emazon.transaction.application.services.mapper;

import com.emazon.transaction.application.dto.rest.CreateSupplyRequestDto;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.infraestructure.entities.SupplyEntity;

public class SupplyMapper {

    public static Supply dtoToDomain(CreateSupplyRequestDto createSupplyRequestDto){
        Supply supply = new Supply();
        supply.setArticleId(createSupplyRequestDto.getArticleId());
        supply.setCost(createSupplyRequestDto.getCost());
        supply.setSupplyDate(createSupplyRequestDto.getSupplyDate());
        supply.setDescription(createSupplyRequestDto.getDescription());
        supply.setQuantity(createSupplyRequestDto.getQuantity());
        supply.setProviderName(createSupplyRequestDto.getProviderName());
        return supply;
    }

    public static Supply entityToDomain(SupplyEntity supplyEntity){
        Supply supply = new Supply();
        supply.setId(supplyEntity.getId());
        supply.setArticleId(supplyEntity.getArticleId());
        supply.setCost(supplyEntity.getCost());
        supply.setSupplyDate(supplyEntity.getSupplyDate());
        supply.setReceivedAt(supplyEntity.getReceivedDate());
        supply.setDescription(supplyEntity.getDescription());
        supply.setQuantity(supplyEntity.getQuantity());
        supply.setCreatedByAuxiliaryId(supplyEntity.getCreatedByAuxiliaryId());
        supply.setProviderName(supplyEntity.getProviderName());
        supply.setSyncStatus(supplyEntity.getSyncStatus());
        supply.setDeliveryStatus(supplyEntity.getDeliveryStatus());
        supply.setCreatedDate(supplyEntity.getCreatedDate());
        return supply;
    }

    public static SupplyEntity domainToEntity(Supply supply){
        SupplyEntity supplyEntity = new SupplyEntity();
        supplyEntity.setId(supply.getId());
        supplyEntity.setArticleId(supply.getArticleId());
        supplyEntity.setCost(supply.getCost());
        supplyEntity.setSupplyDate(supply.getSupplyDate());
        supplyEntity.setDescription(supply.getDescription());
        supplyEntity.setQuantity(supply.getQuantity());
        supplyEntity.setCreatedByAuxiliaryId(supply.getCreatedByAuxiliaryId());
        supplyEntity.setProviderName(supply.getProviderName());
        supplyEntity.setSyncStatus(supply.getSyncStatus());
        supplyEntity.setDeliveryStatus(supply.getDeliveryStatus());
        supplyEntity.setCreatedDate(supply.getCreatedDate());
        return supplyEntity;
    }

}
