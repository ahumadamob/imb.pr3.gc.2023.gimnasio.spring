package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.Person;

public interface IPersonService {
	
	List<Person> getAllPersons();
    Person getPersonById(Integer id);
    Person createPerson(Person person);
    Person editPerson(Integer id, Person person);
    void deletePerson(Integer id);
    boolean exists(Integer id);
	

}
