package com.emazon.transaction.domain.model.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long id_categoria;
    private String name;
    private String description;
}
