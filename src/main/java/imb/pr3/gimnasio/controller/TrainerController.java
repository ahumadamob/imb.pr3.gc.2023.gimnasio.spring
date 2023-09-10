package imb.pr3.gimnasio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.gimnasio.entity.Trainer;
import imb.pr3.gimnasio.service.iTrainerService;

@RestController
@RequestMapping("/api/v1/trainer")
public class TrainerController {

	List<Trainer> trainers = new ArrayList<Trainer>();
	
	@Autowired
	iTrainerService service;
	
	@GetMapping("")
	public List<Trainer> getAllTrainers() {
	
		return service.getAllTrainers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Object> getTrainerById(@PathVariable Integer id) {
		Trainer trainer = service.getTrainerById(id);
		if (trainer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un entrenador con el ID proporcionado");
		} else {
			return ResponseEntity.ok(trainer);
		}
	}
	
	@PostMapping("")
	public Trainer saveTrainer(@RequestBody Trainer entity){
		return service.saveTrainer(entity);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity <Object> editTrainer(@RequestBody Trainer entity){
		Trainer existingTrainer = service.getTrainerById(entity.getId());
		if (existingTrainer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un entrenador con el ID proporcionado");
		} else {
			Trainer updatedTrainer = service.editTrainer(entity);
			return ResponseEntity.ok(updatedTrainer);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <Object> deleteTrainer(@PathVariable Integer id){
		Trainer trainer = service.getTrainerById(id);
		if (trainer == null) {
			return ResponseEntity.status(HttpStatus.OK).body("No se encontró un entrenador con el ID proporcionado");
		} else {
			service.deleteTrainer(id);
			return ResponseEntity.status(HttpStatus.OK).body("Entrenador eliminado");
		}
	}
	
}
