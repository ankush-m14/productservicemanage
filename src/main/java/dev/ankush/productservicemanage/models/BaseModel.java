package dev.ankush.productservicemanage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

}

//"@MappedSuperclass":-
// is a JPA (Java Persistence API) annotation that is used to
// denote a superclass whose mapping information is applied to the entities that inherit from it.

//Definition in a Superclass:
//          An entity class annotated with @MappedSuperclass is considered a superclass
//          that contains common fields and mappings shared by multiple entities.

//Inheritance by Entities:
//                Entities can extend the superclass annotated with @MappedSuperclass.
//                When they do, they inherit the mappings defined in the superclass.

//No Table Mapping:
//        The superclass itself is not mapped to a database table.
//        Instead, its fields and mappings are inherited by the entities that extend it.

//No table created to parent class,only child table were created.Each child will have
// all attributes.

//@GeneratedValue:-
// is a JPA (Java Persistence API) annotation used to specify the
// strategy for the generation of primary key values for entities.
// It is typically applied to a field or property within an entity class
// that represent the primary key