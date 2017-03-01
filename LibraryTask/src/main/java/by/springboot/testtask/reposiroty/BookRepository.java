package by.springboot.testtask.reposiroty;

import by.springboot.testtask.domain.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Тёма on 22.02.2017.
 */
@Repository
public interface BookRepository  extends CrudRepository<Book, Integer >{

}
