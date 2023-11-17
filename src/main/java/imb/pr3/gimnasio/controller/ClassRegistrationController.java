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

import imb.pr3.gimnasio.entity.ClassRegistration;
import imb.pr3.gimnasio.service.IClassRegistrationService;
import imb.pr3.gimnasio.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/class-reg")
public class ClassRegistrationController {

	@Autowired
	IClassRegistrationService classRegService;
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<ClassRegistration>>> getAllClassRegistrations() {
		return classRegService.getAll().isEmpty() ? ResponseUtil.notFound("No se encuentra ningún registro. Para utilizar esta función, primero debe crearlos.")
								      : ResponseUtil.success(classRegService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<ClassRegistration>> getClassRegistrationById(@PathVariable Integer id) {
		return classRegService.exists(id) ? ResponseUtil.success(classRegService.getById(id))
										 : ResponseUtil.notFound("No se encontró ningún registro con ese ID.");
	}

	@PostMapping("")
	public ResponseEntity<APIResponse<ClassRegistration>> saveClassRegistration(@RequestBody ClassRegistration entity){

		return classRegService.exists(entity.getId()) ? ResponseUtil.badRequest("Ya existe un registro con ese ID. Para editar uno, use PUT.")
													 : ResponseUtil.created(classRegService.save(entity));
	}
	
	@PutMapping("")
	public ResponseEntity<APIResponse<ClassRegistration>> editClassRegistration(@RequestBody ClassRegistration entity){
		return classRegService.exists(entity.getId()) ? ResponseUtil.success(classRegService.save(entity))
													 : ResponseUtil.badRequest("El registro especificado no existe.");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<ClassRegistration>> deleteClassRegistration(@PathVariable Integer id){
		return classRegService.exists(id) ? ResponseUtil.successDeleted("Registro eliminado correctamente.",classRegService.delete(classRegService.getById(id).getId()))
										 : ResponseUtil.notFound("No se encontró ningún registro con ese ID.");
	}
	
	//manejador de excepciones para cualquier tipo de error que pueda ser ajeno al programa.
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<ClassRegistration>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<ClassRegistration>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
	
	
}
