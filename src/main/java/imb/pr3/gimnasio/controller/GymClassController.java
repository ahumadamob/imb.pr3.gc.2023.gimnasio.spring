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
import imb.pr3.gimnasio.entity.GymClass;
import imb.pr3.gimnasio.service.IGymClassService;
import imb.pr3.gimnasio.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;


@RestController
@RequestMapping("/api/v1/gymclass")
public class GymClassController {

	@Autowired
	IGymClassService service;

	/*método que busca todas las clases de gimnasio disponibles
	 * Devuelve un objeto ResponseEntity<APIResponse<List<GymClass>>>. 
	 * Es decir que el método responderá a una solicitud HTTP y 
	 * devolverá una respuesta encapsulada en un objeto ResponseEntity 
	 * Retornará una lista de objetos GymClass
	 * service.getAll es un servicio que recuperará una lista de todas las clases de gimnasio disponibles en este caso, en la bbdd
	 * isEmpty() verifica si hay alguna clase creada
	 * "?" es una operación ternaria, en este caso, si la lista está vacía se ejecutará ResponseUtil.notFound()
	 * Si la lista tiene al menos un elemento, se ejecutará ResponseUtil.success()
	 * ResponseUtil.notFound() es un método de la clase ResponseUtil que genera una respuesta HTTP del tipo 404, agregando además un mensaje personalizado
	 * ResponseUtil.success()  genera una respuesta de exito (HTTP 200) y devuelve la lista de clases de gimnasio que obuvo a través de getAll(.
	 * */
	@GetMapping("")
	public ResponseEntity<APIResponse<List<GymClass>>>getAllGymClass() {

		return service.getAll().isEmpty() ? ResponseUtil.notFound("There are no created gym class. Please, first create a gym class with POST verb")
			      : ResponseUtil.success(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<GymClass>> getGymClassById(@PathVariable("id") Integer id){
		return service.exists(id) ? ResponseUtil.success(service.getById(id))
				 : ResponseUtil.notFound("Gym class not found by id.");
	}

	@PostMapping("")
	public ResponseEntity<APIResponse<GymClass>> createGymClass(@RequestBody GymClass gymClass) {

		return service.exists(gymClass.getId()) ? ResponseUtil.badRequest("Already exists a class for the id. To upgrade, please use PUT.")
				 : ResponseUtil.created(service.save(gymClass));
	}

	@PutMapping("")
	public ResponseEntity<APIResponse<GymClass>> editGymClass(@RequestBody GymClass gymClass) {
		return service.exists(gymClass.getId()) ? ResponseUtil.success(service.save(gymClass))
				 : ResponseUtil.badRequest("The gym class doesn't exist.");
		}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Boolean>> deleteGymClass(@PathVariable("id") Integer id){
		return service.exists(id) ? ResponseUtil.success(service.delete(service.getById(id).getId()))
				 : ResponseUtil.notFound("Gym class not found by id.");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<GymClass>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<GymClass>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
}
