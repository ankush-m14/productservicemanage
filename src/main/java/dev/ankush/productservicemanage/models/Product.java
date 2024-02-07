package dev.ankush.productservicemanage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;

    private String description;

    private String image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    private double price;

    @ManyToMany(mappedBy = "productList", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

}
