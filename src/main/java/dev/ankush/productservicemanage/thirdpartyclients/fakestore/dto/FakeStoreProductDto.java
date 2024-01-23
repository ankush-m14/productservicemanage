package dev.ankush.productservicemanage.thirdpartyclients.fakestore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private String id;
    private String title;
    private String category;
    private double price;
    private String description;
    private String image;

}

//We creat two dto FakeStoreProductDto and second is GenericProductDto
//this FakeStoreProductDto is tightly bound with outside API to interact with third party
// one Dto i.e. GenericProductDto is for talk to outside world when we're interacting with
//our own client