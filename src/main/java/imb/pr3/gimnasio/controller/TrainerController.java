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
	
	/**
	 * Crea una nueva entidad "Trainer" (entrenador). Viene representada en el elemento 'entity'
	 * 
	 * @param entity ResponseEntity permite devolver una respuesta HTTP, para especificar el tipo de operacion que se concreta.
	 * 
	 * @return ResponseEntity cuya respuesta está estandarizada y estructurada, dividida en 'estado', 'datos' y 'mensaje'.
	 * 		-Si la entidad Trainer se crea con éxito, se registra en la BBDD y se obtiene como respuesta: '201', la nueva entidad y 'null'. 
	 * 		-Si ya existe una entidad Trainer con el mismo ID, se obtiene una como respuesta: '400', datos nulos y un mensaje indicando el error.
	 * 		 y se proporciona un mensaje de error gracias al APIResponse.
	 * 
	 * @PostMapping Notacion de Spring que indica que el metodo va a manejar solicitudes HTTP del tipo POST que lleguen a la URL.
	 * 				Esta notacion permite crear un nuevo entranador con el metodo que le sigue.
	 * 				En este caso se utilizo Postman para realizar pruebas de funcionamiento.
	 * 	
	 * @RequestBody 'Trainer entity' toma un objeto 'Trainer' como entrada en una solicitud HTTP. Se utiliza en este caso porque se trata de una creacion.
	 * 			
	 */
	@PostMapping("")
	public ResponseEntity<APIResponse<Trainer>> saveTrainer(@RequestBody Trainer entity){
		// Se aprovecha que los retornos son de solo una linea de codigo usando una condicion ternaria,
		// que verifica si ya existe un entrenador con el mismo ID en la BBDD
		// Si ya existe, se retorna con 'ResponseUtil' ResponseEntity del tipo BAD_REQUEST, pasando como parametro String al ResponseUtil un mensaje de error.
		return trainerService.exists(entity.getId()) ? ResponseUtil.badRequest("Ya existe un entrenador con ese ID. Para editar un entrenador, use PUT.")
		//	Si no existe, se retorna con 'ResponseUtil' una 'ResponseEntity' del tipo CREATED, llamando al metodo save() del servicio,
		//y pasandole como parametro la entidad (entity) al 'ResponseUtil'	
													 : ResponseUtil.created(trainerService.save(entity));
	}
	
	@PutMapping("")
	public ResponseEntity<APIResponse<Trainer>> editTrainer(@RequestBody Trainer entity){
		return trainerService.exists(entity.getId()) ? ResponseUtil.success(trainerService.save(entity))
													 : ResponseUtil.badRequest("El entrenador especificado no existe.");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Trainer>> deleteTrainer(@PathVariable Integer id){
		return trainerService.exists(id) ? ResponseUtil.successDeleted("Entrenador eliminado correctamente.",trainerService.delete(trainerService.getById(id).getId()))
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
