package by.springboot.testtask.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "history")
public class History {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="date",nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "id_book", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;


}
