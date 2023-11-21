package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.Trainer;

public interface ITrainerService {

	List<Trainer> getAll();
	Trainer getById(Integer id);
	Trainer save(Trainer trainer);
	Trainer delete(Integer id);
	boolean exists(Integer id);
	List<Trainer> findByEnabled(boolean enabled);
}
