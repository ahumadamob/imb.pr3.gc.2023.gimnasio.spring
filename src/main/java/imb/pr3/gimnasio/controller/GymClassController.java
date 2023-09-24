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

import imb.pr3.gimnasio.entity.GymClass;
import imb.pr3.gimnasio.service.iGymClassService;


@RestController
@RequestMapping("/api/v1/gymclass")
public class GymClassController {

	@Autowired
	iGymClassService service;

	@GetMapping("")
	public ResponseEntity<APIResponse<List<GymClass>>>getAllGymClass() {

		List<GymClass> gymClass = service.getAllGymClasses();
		if(gymClass.isEmpty()) {
			APIResponse<List<GymClass>> response = new APIResponse<List<GymClass>> (200, null, service.getAllGymClasses());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			APIResponse<List<GymClass>> response = new APIResponse<List<GymClass>> (200, null, gymClass);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<GymClass>> getGymClassById(@PathVariable("id") Integer id){
		GymClass gymClass = service.getGymClassById(id);
		if(gymClass == null) {
			List <String> messages = new ArrayList<>();
			messages.add("Cannot found a gym class for id: " + id.toString());
			messages.add("Please, review the parameter.");
			APIResponse<GymClass> response = new APIResponse<GymClass>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		else {
			APIResponse<GymClass> response = new APIResponse<GymClass>(HttpStatus.OK.value(), null, gymClass);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@PostMapping("")
	public ResponseEntity<APIResponse<GymClass>> createGymClass(@RequestBody GymClass gymClass) {

		if(gymClass.getId() != null) {
			GymClass searchGymClass = service.getGymClassById(gymClass.getId());

			if(searchGymClass!=null) {
				List <String> messages = new ArrayList<>();
				messages.add("Already exists a gym class for the id: " + gymClass.getId().toString());
				messages.add("For upgrading, use PUT verb");
				APIResponse<GymClass> response = new APIResponse<GymClass>(HttpStatus.BAD_REQUEST.value(), messages, null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		}

		service.createGymClass(gymClass);
		APIResponse<GymClass> response = new APIResponse<GymClass>(HttpStatus.CREATED.value(), null, gymClass);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		//TODO: add a message


	}

	@PutMapping("")
	public ResponseEntity<APIResponse<GymClass>> editGymClass(@RequestBody GymClass gymClass) {

		boolean isError;
		String idStr;

		if(gymClass.getId() != null) {
			GymClass searchGymClass = service.getGymClassById(gymClass.getId());
			idStr = gymClass.getId().toString();

			if(searchGymClass!=null) {
				isError = false;
			}
			else {
				isError = true;
			}
		}
		else{
			isError = true;
			idStr = "undefined";
		}

		if(isError) {

			List <String> messages = new ArrayList<>();
			messages.add("Doesn't exist a Gym Class for the id: " + idStr);
			messages.add("For creating a new Gym Clase, use POST verb");
			APIResponse<GymClass> response = new APIResponse<GymClass>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}

		else {
			service.createGymClass(gymClass);
			APIResponse<GymClass> response = new APIResponse<GymClass>(HttpStatus.OK.value(), null, gymClass);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<GymClass>> deleteGymClass(@PathVariable("id") Integer id){
		GymClass searchGymClass = service.getGymClassById(id);

		if(searchGymClass == null) {
			List <String> messages = new ArrayList<>();
			messages.add("Doesn't exist a Gym Class for the id: " + id.toString());
			APIResponse<GymClass> response = new APIResponse<GymClass>(HttpStatus.OK.value(), null, searchGymClass);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}

		else {
			service.deleteGymClass(id);
			List <String> messages = new ArrayList<>();
			messages.add("The gym class with the id " + id.toString() + " was succesfull deleted");
			APIResponse<GymClass> response = new APIResponse<GymClass>(HttpStatus.OK.value(), messages, searchGymClass);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

}
