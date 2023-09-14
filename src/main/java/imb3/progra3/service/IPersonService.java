package imb3.progra3.service;

import java.util.List;
import imb3.progra3.entity.Person;

public interface IPersonService {
	
	List<Person> getAllPersons();
    Person getPersonById(Integer id);
    Person createPerson(Person person);
    Person editPerson(Integer id, Person person);
    void deletePerson(Integer id);
	

}
