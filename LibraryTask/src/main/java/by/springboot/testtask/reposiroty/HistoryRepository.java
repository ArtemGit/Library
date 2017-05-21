package by.springboot.testtask.reposiroty;


import by.springboot.testtask.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<Book, Integer > {

}
