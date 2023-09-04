package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.Trainer;

public interface iTrainerService {

	List<Trainer> getAllTrainers() throws Exception;
	Trainer getTrainerById(Integer id) throws Exception;
	Trainer saveTrainer(Trainer trainer) throws Exception;
	Trainer editTrainer(Integer id, Trainer trainer) throws Exception;
	boolean deleteTrainer(Integer id) throws Exception;
	
}
