package by.springboot.testtask.domain;


import javax.persistence.*;

@Entity
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

    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idbook) {
        this.idbook = idbook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicationPlace() {
        return publicationPlace;
    }

    public void setPublicationPlace(String publicationPlace) {
        this.publicationPlace = publicationPlace;
    }

    public String getPublishHouse() {
        return publishHouse;
    }

    public void setPublishHouse(String publishHouse) {
        this.publishHouse = publishHouse;
    }

    public int getPublishStartYear() {
        return publishStartYear;
    }

    public void setPublishStartYear(int publishStartYear) {
        this.publishStartYear = publishStartYear;
    }

    public int getPublishEndYear() {
        return publishEndYear;
    }

    public void setPublishEndYear(int publishEndYear) {
        this.publishEndYear = publishEndYear;
    }

    public int getElectVerStartYear() {
        return electVerStartYear;
    }

    public void setElectVerStartYear(int electVerStartYear) {
        this.electVerStartYear = electVerStartYear;
    }

    public int getElectVerEndYear() {
        return electVerEndYear;
    }

    public void setElectVerEndYear(int electVerEndYear) {
        this.electVerEndYear = electVerEndYear;
    }

    public long getIssn() {
        return issn;
    }

    public void setIssn(long issn) {
        this.issn = issn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
