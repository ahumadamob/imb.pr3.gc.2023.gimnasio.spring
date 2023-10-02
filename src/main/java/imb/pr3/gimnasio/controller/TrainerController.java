package imb.pr3.gimnasio.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.gimnasio.entity.Trainer;
import imb.pr3.gimnasio.service.ITrainerService;
import imb.pr3.gimnasio.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/trainer")
public class TrainerController {
	
	@Autowired
	ITrainerService trainerService;
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<Trainer>>> getAllTrainers() {
		return trainerService.getAll().isEmpty() ? ResponseUtil.notFound("No se encuentra ningún registro. Para utilizar esta función, primero debe crearlos.")
								      : ResponseUtil.success(trainerService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Trainer>> getTrainerById(@PathVariable Integer id) {
		return trainerService.exists(id) ? ResponseUtil.success(trainerService.getById(id))
										 : ResponseUtil.notFound("No se encontró ningún entrenador con ese ID.");
	}
	
	@PostMapping("")
	public ResponseEntity<APIResponse<Trainer>> saveTrainer(@RequestBody Trainer entity){
		return trainerService.exists(entity.getId()) ? ResponseUtil.badRequest("Ya existe un entrenador con ese ID. Para editar un entrenador, use PUT.")
													 : ResponseUtil.created(trainerService.save(entity));
	}
	
	@PutMapping("")
	public ResponseEntity<APIResponse<Trainer>> editTrainer(@RequestBody Trainer entity){
		return trainerService.exists(entity.getId()) ? ResponseUtil.success(trainerService.save(entity))
													 : ResponseUtil.badRequest("El entrenador especificado no existe.");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Trainer>> deleteTrainer(@PathVariable Integer id){
		return trainerService.exists(id) ? ResponseUtil.success(trainerService.delete(trainerService.getById(id).getId()))
										 : ResponseUtil.notFound("No se encontró ningún entrenador con ese ID.");
	}
	
	//manejador de excepciones para cualquier tipo de error que pueda ser ajeno al programa.
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Trainer>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Trainer>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
	
	
}
