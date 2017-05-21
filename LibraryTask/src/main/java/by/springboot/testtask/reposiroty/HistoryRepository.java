package by.springboot.testtask.reposiroty;


import by.springboot.testtask.domain.History;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<History, Integer > {

}
