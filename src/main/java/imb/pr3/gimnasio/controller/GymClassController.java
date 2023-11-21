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
	IGymClassService gymClassService;

	@GetMapping("")
	public ResponseEntity<APIResponse<List<GymClass>>>getAllGymClass() {

		return gymClassService.getAll().isEmpty() ? ResponseUtil.notFound("No se encuentra ningún registro. Para utilizar esta función, primero debe crearlos.")
			      								  : ResponseUtil.success(gymClassService.getAll());
	}
	
	@GetMapping("/enabled")
	public ResponseEntity<APIResponse<List<GymClass>>>getAllEnabledGymClasses() {
		
		return gymClassService.findByEnabled(true).isEmpty() ? ResponseUtil.notFound("No hay clases del gimnasio que estén habilitadas.")
															 : ResponseUtil.success(gymClassService.findByEnabled(true));
	}

	@GetMapping("/disabled")
	public ResponseEntity<APIResponse<List<GymClass>>>getAllDisabledGymClasses() {
		
		return gymClassService.findByEnabled(false).isEmpty() ? ResponseUtil.notFound("No hay clases del gimnasio que estén deshabilitadas.")
															 : ResponseUtil.success(gymClassService.findByEnabled(false));
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<GymClass>> getGymClassById(@PathVariable("id") Integer id){
		return gymClassService.exists(id) ? ResponseUtil.success(gymClassService.getById(id))
				 						  : ResponseUtil.notFound("No se encontró ninguna clase con ese ID.");
	}

	@PostMapping("")
	public ResponseEntity<APIResponse<GymClass>> saveGymClass(@RequestBody GymClass gymClass) {

		return gymClassService.exists(gymClass.getId()) ? ResponseUtil.badRequest("Ya existe una clase con ese ID. Para editar una, use PUT.")
				 										: ResponseUtil.created(gymClassService.save(gymClass));
	}

	@PutMapping("")
	public ResponseEntity<APIResponse<GymClass>> editGymClass(@RequestBody GymClass gymClass) {
		return gymClassService.exists(gymClass.getId()) ? ResponseUtil.success(gymClassService.save(gymClass))
				 										: ResponseUtil.badRequest("La clase especificada no existe.");
	}
	
	@PutMapping("/enable/{id}")
	public ResponseEntity<APIResponse<GymClass>> enableGymClass(@PathVariable("id") Integer id){
		if (gymClassService.exists(id)) {
			GymClass gymClass = gymClassService.getById(id);
			if (gymClass.isEnabled()) {
				return ResponseUtil.badRequest("Esa clase ya está habilitada.");
			} else {
				gymClassService.getById(id).setEnabled(true);
				gymClassService.save(gymClassService.getById(id));
				return ResponseUtil.success(gymClassService.getById(id));
			}
		} else {
			return ResponseUtil.notFound("No se encuentra esa clase. Revise el ID proporcionado.");
		}

	}
	
	@PutMapping("/disable/{id}")
	public ResponseEntity<APIResponse<GymClass>> disableGymClass(@PathVariable("id") Integer id){
		if (gymClassService.exists(id)) {
			GymClass gymClass = gymClassService.getById(id);
			if (gymClass.isEnabled()) {
				gymClassService.getById(id).setEnabled(false);
				gymClassService.save(gymClassService.getById(id));
				return ResponseUtil.success(gymClassService.getById(id));
			} else {
				return ResponseUtil.badRequest("Esa clase ya está deshabilitada.");
			}
		} else {
			return ResponseUtil.notFound("No se encuentra esa clase. Revise el ID proporcionado.");
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<GymClass>> deleteGymClass(@PathVariable("id") Integer id){
		return gymClassService.exists(id) ? ResponseUtil.successDeleted("Clase eliminada correctamente.", gymClassService.delete(gymClassService.getById(id).getId()))
				 						  : ResponseUtil.notFound("No se encontró ninguna clase con ese ID.");
	}
	
	@DeleteMapping("delete-disabled/{id}")
	public ResponseEntity<APIResponse<GymClass>> deleteDisabledGymClass(@PathVariable("id") Integer id){
		if (gymClassService.exists(id)) {
			GymClass gymClass = gymClassService.getById(id);
			if (gymClass.isEnabled()) {
				return ResponseUtil.badRequest("No puede eliminarse la clase porque está habilitada.");
			} else {
				return ResponseUtil.successDeleted("Clase eliminada", gymClassService.delete(gymClass.getId()));
			}
		} else {
			return ResponseUtil.badRequest("Esa clase no existe.");
		}
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
