package imb3.progra3.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb3.progra3.entity.Person;
import imb3.progra3.repository.PersonRepository;
import imb3.progra3.service.IPersonService;

@Service
public class PersonServicempl implements IPersonService {

	@Autowired
	PersonRepository repo; 

    @Override
    public List<Person> getAllPersons() {
        return repo.findAll();
    }

    @Override
    public Person getPersonById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Person createPerson(Person person) {
        return repo.save(person);
    }

    @Override
    public Person editPerson(Integer id, Person person) {
        if (repo.existsById(id)) {
            person.setId(id);
            return repo.save(person);
        }
        return null;
    }

    @Override
    public void deletePerson(Integer id) {
    	repo.deleteById(id);
    }
}