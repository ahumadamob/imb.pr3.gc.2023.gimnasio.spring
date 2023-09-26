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
import imb.pr3.gimnasio.service.ITrainerService;

@RestController
@RequestMapping("/api/v1/trainer")
public class TrainerController {

	
	@Autowired
	ITrainerService service;
	
	@GetMapping("")
	public APIResponse<List<Trainer>> getAll() {
		
		List<String> messages = new ArrayList<>();
		messages.add("Listado de entrenadores mostrado con éxito.");
		List<Trainer> trainerList = service.getAllTrainers();
		
		APIResponse<List<Trainer>> response = new APIResponse<>(200, messages, trainerList);
		
		return response;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Trainer>> getById(@PathVariable Integer id) {
		List<String> messages = new ArrayList<>();
		Integer status;
		Trainer trainerById = service.getTrainerById(id);
		
		if (service.exists(id)) {
			status = HttpStatus.OK.value();
			messages.add("Entrenador encontrado.");
		} else {
			status = HttpStatus.BAD_REQUEST.value();
			messages.add("No se encuentra un entrenador con ese ID");
		}
		
		APIResponse<Trainer> response = new APIResponse<>(status, messages, trainerById);
		return ResponseEntity.status(status).body(response);	
		
	}
	
	@PostMapping("")
	public ResponseEntity<APIResponse<Trainer>> save(@RequestBody Trainer entity){
		
		List<String> messages = new ArrayList<>();
		
		if (service.exists(entity.getId())) {
			messages.add("Ese usuario ya existe. Para editar, use PUT");
			APIResponse<Trainer> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), messages, service.getTrainerById(entity.getId()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} else {
			messages.add("Entrenador creado con éxito");
			APIResponse<Trainer> response = new APIResponse<>(HttpStatus.OK.value(), messages, service.saveTrainer(entity));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
	}
	
	@PutMapping("")
	public ResponseEntity<APIResponse<Trainer>> edit(@RequestBody Trainer entity){
		List<String> messages = new ArrayList<>();
		Integer status;
		
		if (service.exists(entity.getId())) {
			status = HttpStatus.OK.value();
			messages.add("Entrenador editado con éxito");
			APIResponse<Trainer> response = new APIResponse<>(status, messages, service.saveTrainer(entity));
			return ResponseEntity.status(status).body(response);
		} else {
			status = HttpStatus.BAD_REQUEST.value();
			messages.add("Ese usuario NO existe.");
			APIResponse<Trainer> response = new APIResponse<>(status, messages, service.getTrainerById(entity.getId()));
			return ResponseEntity.status(status).body(response);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Trainer>> delete(@PathVariable Integer id){
		Trainer trainer = service.getTrainerById(id);
		List<String> messages = new ArrayList<>();
		Integer status;
		
		if (service.exists(id)) {
			status = HttpStatus.OK.value();
			messages.add("Entrenador eliminado.");
			service.deleteTrainer(id);
		} else {
			status = HttpStatus.BAD_REQUEST.value();
			messages.add("ID no encontrado.");
		}
		
		APIResponse<Trainer> response = new APIResponse<>(status, messages, trainer);
		return ResponseEntity.status(status).body(response);
		
	}
	
}
