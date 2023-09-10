package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.Trainer;

public interface iTrainerService {

	List<Trainer> getAllTrainers();
	Trainer getTrainerById(Integer id);
	Trainer saveTrainer(Trainer trainer);
	Trainer editTrainer(Trainer trainer);
	Trainer deleteTrainer(Integer id);
	
}
