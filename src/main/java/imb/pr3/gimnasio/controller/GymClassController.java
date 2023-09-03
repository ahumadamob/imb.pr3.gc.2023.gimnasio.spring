package imb.pr3.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.gimnasio.entity.GymClass;
import imb.pr3.gimnasio.service.iGymClassService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/GymClass")
public class GymClassController {
	@Autowired
	private iGymClassService GymClassService;

	@GetMapping("")
	public ResponseEntity <?> getAll(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(GymClassService.getAllGymClass());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, Please try again.\"}");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity <?> getOne(@PathVariable Integer id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(GymClassService.getGymClassById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Gym class doesn't exist.\"}");
		}
	}

	@PostMapping("")
	public ResponseEntity <?> save(@RequestBody GymClass entity){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(GymClassService.createGymClass(entity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error creating class, Please try again.\"}");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity <?> edit(@PathVariable Integer id, @RequestBody GymClass entity){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(GymClassService.editGymClass(id, entity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error editting class, Please try again.\"}");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity <?> delete(@PathVariable Integer id){
		try {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(GymClassService.deleteGymClass(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error deleting, gym class doesn't exist.\"}");
		}
	}
}
