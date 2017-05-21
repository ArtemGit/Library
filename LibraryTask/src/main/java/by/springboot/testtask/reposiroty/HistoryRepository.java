package by.springboot.testtask.reposiroty;


import by.springboot.testtask.domain.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<History, Integer > {

}
