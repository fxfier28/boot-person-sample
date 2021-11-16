package id.co.allianz.demo.bootperson.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import id.co.allianz.demo.bootperson.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    @Query(value = "select * from calcdob(:id)", nativeQuery = true)
    Integer calcdob(Integer id);
}
