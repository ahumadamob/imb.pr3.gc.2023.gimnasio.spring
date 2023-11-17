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
