package imb.pr3.gimnasio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public APIResponse<Trainer> getById(@PathVariable Integer id) {
		List<String> messages = new ArrayList<>();
		Integer status;
		Trainer trainerById = service.getTrainerById(id);
		
		if (this.exists(id)) {
			status = 200;
			messages.add("Entrenador encontrado.");
		} else {
			status = 404;
			messages.add("No se encuentra un entrenador con ese ID");
		}
		
		APIResponse<Trainer> response = new APIResponse<>(status, messages, trainerById);
		return response;
		
		
	}
	

/*	public APIResponse<Trainer> save(@RequestBody Trainer entity){
		List<String> messages = new ArrayList<>();
		Integer status;
		
		Trainer saveTrainer = service.saveTrainer(entity);
		if (entity != service.getTrainerById(entity.getId())) {
			status = 200;
			messages.add("Ese ID ya existe. Se ha actualizado un entrenador");
		} else {
			status = 200;
			messages.add("Entrenador creado con éxito");
		}
		APIResponse<Trainer> response = new APIResponse<>(status, messages, saveTrainer);
		return response;
	}
*/		
	@PostMapping("")
	public APIResponse<Trainer> save(@RequestBody Trainer entity){
		
		List<String> messages = new ArrayList<>();
		Integer status;
		
		if (this.exists(entity.getId())) {
			status = 404;
			messages.add("Ese usuario ya existe. Para editar, use PUT");
			APIResponse<Trainer> response = new APIResponse<>(status, messages, service.getTrainerById(entity.getId()));

			return response;
		} else {
			status = 200;
			messages.add("Entrenador creado con éxito");
			APIResponse<Trainer> response = new APIResponse<>(status, messages, service.saveTrainer(entity));
			return response;
		}
	}
	
	
@PutMapping("")
/*	 	public Trainer edit(@RequestBody Trainer entity) {
		Trainer editTrainer = service.saveTrainer(entity);
		List<String> messages = new ArrayList<>();
		Integer status;
		
		if (this.exists(entity.getId())) {
			
		} else {
			
		}
	}
 */	
	public APIResponse<Trainer> edit(@RequestBody Trainer entity){
		List<String> messages = new ArrayList<>();
		Integer status;
		
		if (this.exists(entity.getId())) {
			status = 200;
			messages.add("Entrenador editado con éxito");
			APIResponse<Trainer> response = new APIResponse<>(status, messages, service.saveTrainer(entity));
			return response;
		} else {
			status = 404;
			messages.add("Ese usuario NO existe.");
			APIResponse<Trainer> response = new APIResponse<>(status, messages, service.getTrainerById(entity.getId()));
			return response;
		}
}
	
	
	@DeleteMapping("/{id}")
	public APIResponse<Trainer> delete(@PathVariable Integer id){
		Trainer trainer = service.getTrainerById(id);
		List<String> messages = new ArrayList<>();
		Integer status;
		
		if (trainer == null) {
			status = 404;
			messages.add("ID no encontrado.");
		} else {
			status = 200;
			messages.add("Entrenador eliminado.");
			service.deleteTrainer(id);
		}
		APIResponse<Trainer> response = new APIResponse<>(status, messages, trainer);
		return response;
		
	}
	
	private boolean exists (Integer id) {
		if(id == null) {
			return false;
		} else {
			Trainer trainer = service.getTrainerById(id);
			if(trainer == null) {
				return false;
			} else {
				return true;
			}
		}
	}
	
}
