package imb.pr3.gimnasio.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.gimnasio.entity.Trainer;
import imb.pr3.gimnasio.repository.TrainerRepository;
import imb.pr3.gimnasio.service.ITrainerService;

@Service
public class TrainerServiceImpl implements ITrainerService{

	@Autowired
	TrainerRepository repo;
	
	
	@Override
	public List<Trainer> getAll() {
			return repo.findAll();
	}

	@Override
	public Trainer getById(Integer id) {
		Optional<Trainer> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}
	

	@Override
	public Trainer save(Trainer trainer) {
		repo.save(trainer);
		return trainer;
	}

	@Override
	public Trainer delete(Integer id) {
		repo.deleteById(id);
		return null;
		
	}

	@Override
	public boolean exists(Integer id) {
		return (id == null) ? false : repo.existsById(id);
	}

	


}
