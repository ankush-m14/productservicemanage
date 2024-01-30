package dev.ankush.productservicemanage.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> product;

}

//What is an @Entity?
//In Java, when you're building a program that needs to store information in a database,
//an @Entity is like a special tag you put on a class to say,
//"Hey, I want instances of this class to be saved in a database."

//What Does It Do?
//When you mark a class with @Entity, it tells a part of your program
// (usually a thing called an ORM, like Hibernate) to treat objects
// of that class as if they were rows in a table in a database.

// "  @OneToMany
//    private List<Product> product;" :-
           //In category we also mention cardinality in list of product and also mention
           //cardinality on category in product class but this two are same we know that
          // but spring will create a different column for both of them to avoid ambiguity problem
          //So to solve this problem and tell spring both are same and not need create separate
          //column we use mappedBy " @OneToMany(mappedBy = "category")"