package imb.pr3.gimnasio.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.gimnasio.entity.Person;
import imb.pr3.gimnasio.repository.PersonRepository;
import imb.pr3.gimnasio.service.IPersonService;

@Service
public class PersonServicempl implements IPersonService {

	@Autowired
	PersonRepository repo; 

    @Override
    public List<Person> getAll() {
        return repo.findAll();
    }

	@Override
	public Person getById(Integer id) {
		Optional<Person> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Person save(Person person) {
		repo.save(person);
		return person;
	}

	@Override
	public Person delete(Integer id) {
		repo.deleteById(id);
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		return (id == null) ? false : repo.existsById(id);
	}
}