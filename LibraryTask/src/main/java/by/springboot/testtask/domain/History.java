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
    int id;


    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_username", nullable = false)
    private User user;
}
