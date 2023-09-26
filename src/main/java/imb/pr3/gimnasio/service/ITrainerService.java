package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.Trainer;

public interface ITrainerService {

	List<Trainer> getAllTrainers();
	Trainer getTrainerById(Integer id);
	Trainer saveTrainer(Trainer trainer);
	Trainer deleteTrainer(Integer id);
	boolean exists(Integer id);
}
