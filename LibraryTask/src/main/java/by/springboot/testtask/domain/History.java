package by.springboot.testtask.domain;



import javax.persistence.*;


@Entity

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}