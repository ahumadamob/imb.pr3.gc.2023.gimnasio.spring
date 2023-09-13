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
	public List<Trainer> getAllTrainers() {
		
			return repo.findAll();
		
	}

	@Override
	public Trainer getTrainerById(Integer id) {
		Optional<Trainer> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
		
	}

	@Override
	public Trainer saveTrainer(Trainer trainer) {
		repo.save(trainer);
		return trainer;
	}



	@Override
	public Trainer deleteTrainer(Integer id) {
		repo.deleteById(id);
		return null;
		
	}


}
