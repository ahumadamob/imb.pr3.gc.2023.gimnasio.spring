package imb.pr3.gimnasio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.gimnasio.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}