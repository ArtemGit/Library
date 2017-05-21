package by.springboot.testtask.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {


    @Id
    @Column(name="id_book")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idbook;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String publicationPlace;

    @Column(name="publish_house",nullable=false)
    private String publishHouse;

    @Column(name="publish_start_year",nullable=false)
    private int publishStartYear;

    @Column(name="publish_end_year")
    private int publishEndYear;

    @Column(name="elect_ver_start_year")
    private int electVerStartYear;

    @Column(name="elect_ver_end_year")
    private int electVerEndYear;

    @Column(name="issn",nullable=false)
    private long issn;

    @Column(name="country",nullable=false)
    private String country;

    @Column(name="lang",nullable=false)
    private String language;

    @Column(name="description",nullable=false, length=1000)
    private String description;

    @Column(name="signed",nullable=false,columnDefinition="boolean default false")
    private Boolean signed;

    @Column(name="image_name")
    private String imageName;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<History> bookHistory = new HashSet<>();


}
