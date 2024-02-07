package dev.ankush.productservicemanage.dto;

import dev.ankush.productservicemanage.models.Orders;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenericProductDto {
    private String id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
    private List<Orders> orders;
}
