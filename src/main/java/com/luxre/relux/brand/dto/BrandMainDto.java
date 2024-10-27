package com.luxre.relux.brand.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BrandMainDto {
    private String brandName;
    private String brandImagePath;
    private String productName;
    private int brandPrice;
}