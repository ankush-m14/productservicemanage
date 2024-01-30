package dev.ankush.productservicemanage.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenericCategoryDto {
    String name;
    List<GenericProductDto> productDtoList;
}
