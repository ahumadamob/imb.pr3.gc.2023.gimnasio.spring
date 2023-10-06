package imb3.progra3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import imb3.progra3.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}