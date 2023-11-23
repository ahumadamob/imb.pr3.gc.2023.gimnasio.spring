package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.Person;

public interface IPersonService {
	
	List<Person> getAll();
    Person getById(Integer id);
    Person save(Person person);
    Person delete(Integer id);
    boolean exists(Integer id);
	
}

