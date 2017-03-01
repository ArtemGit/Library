package by.springboot.testtask.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idbook;

    @Column
    private String name;

    @Column
    private String author;

    @Column
    private int year;

    @Column
    private String description;


}
