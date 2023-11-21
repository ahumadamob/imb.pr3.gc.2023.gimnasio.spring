package imb.pr3.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.gimnasio.entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer>{
	public List<Trainer> findByEnabled(boolean enabled);
}
