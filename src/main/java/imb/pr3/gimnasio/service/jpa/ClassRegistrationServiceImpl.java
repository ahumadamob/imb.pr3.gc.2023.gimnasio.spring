package imb.pr3.gimnasio.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import imb.pr3.gimnasio.entity.ClassRegistration;
import imb.pr3.gimnasio.repository.ClassRegistrationRepository;
import imb.pr3.gimnasio.service.IClassRegistrationService;

@Service
public class ClassRegistrationServiceImpl implements IClassRegistrationService {

	ClassRegistrationRepository repo;
	@Override
	public List<ClassRegistration> getAll() {
		return repo.findAll();
	}

	@Override
	public ClassRegistration getById(Integer id) {
		Optional<ClassRegistration> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public ClassRegistration save(ClassRegistration classregistration) {
		repo.save(classregistration);
		return classregistration;
	}

	@Override
	public ClassRegistration delete(Integer id) {
		repo.deleteById(id);
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		return (id == null) ? false : repo.existsById(id);
	}

}
