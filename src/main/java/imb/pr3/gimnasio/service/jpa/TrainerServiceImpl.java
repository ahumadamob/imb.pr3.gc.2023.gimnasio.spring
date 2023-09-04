package imb.pr3.gimnasio.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import imb.pr3.gimnasio.entity.Trainer;
import imb.pr3.gimnasio.repository.TrainerRepository;
import imb.pr3.gimnasio.service.iTrainerService;

public class TrainerServiceImpl implements iTrainerService{

	@Autowired
	TrainerRepository repo;
	
	
	@Override
	public List<Trainer> getAllTrainers() throws Exception {
		try {
			List<Trainer> trainers = repo.findAll();
			return trainers;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Trainer getTrainerById(Integer id) throws Exception {
		try {
			Optional<Trainer> trainerOptional = repo.findById(id);
			return trainerOptional.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Trainer saveTrainer(Trainer trainer) throws Exception {
		repo.save(trainer);
		try {
			trainer = repo.save(trainer);
			return trainer;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Trainer editTrainer(Integer id, Trainer trainer) throws Exception {
		try {
			Optional<Trainer> trainerOptional = repo.findById(id);
			Trainer trainerMod = trainerOptional.get();
			trainerMod = repo.save(trainer);
			return trainerMod;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean deleteTrainer(Integer id) throws Exception {
		try {
			if(repo.existsById(id)) {
				repo.deleteById(id);
				return true;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
	}
		
	}

}
