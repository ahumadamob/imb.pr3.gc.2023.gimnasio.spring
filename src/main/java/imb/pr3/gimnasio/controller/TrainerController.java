package imb.pr3.gimnasio.controller;

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

	private iTrainerService repo;
	
	@GetMapping("")
	public ResponseEntity<?> getAllTrainers() {
	
		try {
			return ResponseEntity.status(HttpStatus.OK).body(repo.getAllTrainers());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener objetos trainer.");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <?> getTrainerById(@PathVariable Integer id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(repo.getTrainerById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener objeto trainer.");
		}
	}
	
	@PostMapping("")
	public ResponseEntity <?> saveTrainer(@RequestBody Trainer entity){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(repo.saveTrainer(entity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear entidad.");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity <?> editTrainer(@PathVariable Integer id, @RequestBody Trainer entity){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(repo.editTrainer(id,entity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al editar la entidad");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <?> deleteTrainer(@PathVariable Integer id){
		try {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(repo.deleteTrainer(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error eliminando entidad.");
		}
	}
	
}
