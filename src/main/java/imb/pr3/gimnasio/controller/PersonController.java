package imb.pr3.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.gimnasio.entity.Person;
import imb.pr3.gimnasio.service.IPersonService;
import imb.pr3.gimnasio.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping("")
    public ResponseEntity<APIResponse<List<Person>>> getAllPersons() {
    	return personService.getAll().isEmpty() ? ResponseUtil.notFound("No se encontraron registros de personas.")
    												   : ResponseUtil.success(personService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Person>> getPersonById(@PathVariable Integer id) {
    	return personService.exists(id) ? ResponseUtil.success(personService.getById(id))
    									: ResponseUtil.notFound("No se encontró una persona con ese ID");
    }

    @PostMapping("")
    public ResponseEntity<APIResponse<Person>> savePerson(@RequestBody Person person) {
    	return personService.exists(person.getId()) ? ResponseUtil.badRequest("Esa persona ya existe. Para editarla, use PUT")
    												: ResponseUtil.created(personService.save(person));
    }

    @PutMapping("")
    public ResponseEntity<APIResponse<Person>> editPerson(@RequestBody Person person) {
    	return personService.exists(person.getId()) ? ResponseUtil.success(personService.save(person))
				: ResponseUtil.badRequest("No se encontró una persona con ese ID");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Person>> deletePerson(@PathVariable Integer id) {
    	return personService.exists(id) ? ResponseUtil.successDeleted("Persona eliminada",personService.delete(id))
    									: ResponseUtil.notFound("No se encontró una persona con ese ID");
    }
    
}