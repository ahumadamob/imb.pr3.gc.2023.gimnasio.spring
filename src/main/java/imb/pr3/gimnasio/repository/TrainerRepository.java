package imb.pr3.gimnasio.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.gimnasio.entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer>{
}
