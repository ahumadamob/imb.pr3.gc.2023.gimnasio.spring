package imb.pr3.gimnasio.controller;

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

import imb.pr3.gimnasio.entity.Person;
import imb.pr3.gimnasio.service.IPersonService;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<APIResponse<List<Person>>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        if (persons.isEmpty()) {
            List<String> messages = List.of("No se encontraron personas.");
            return createResponse(HttpStatus.OK, messages, null);
        } else {
            return createResponse(HttpStatus.OK, null, persons);
        }
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<APIResponse<Person>> getPersonById(@PathVariable Integer id) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            List<String> messages = List.of("Persona con ID " + id + " no encontrada.");
            return createResponse(HttpStatus.BAD_REQUEST, messages, null);
        } else {
            return createResponse(HttpStatus.OK, null, person);
        }
    }

    @PostMapping("/persons")
    public ResponseEntity<APIResponse<Person>> createPerson(@RequestBody Person person) {
        if (person.getId() != null && exists(person.getId())) {
            List<String> messages = List.of("Persona con ID " + person.getId() + " ya existe. Use PUT para actualizar.");
            return createResponse(HttpStatus.BAD_REQUEST, messages, null);
        }

        Person createdPerson = personService.createPerson(person);
        return createResponse(HttpStatus.CREATED, null, createdPerson);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<APIResponse<Person>> editPerson(@PathVariable Integer id, @RequestBody Person person) {
        if (!exists(id)) {
            List<String> messages = List.of("Persona con ID " + id + " no encontrada. Use POST para crear una nueva persona.");
            return createResponse(HttpStatus.BAD_REQUEST, messages, null);
        }

        Person updatedPerson = personService.editPerson(id, person);
        return createResponse(HttpStatus.OK, null, updatedPerson);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<APIResponse<String>> deletePerson(@PathVariable Integer id) {
        if (!exists(id)) {
            List<String> messages = List.of("Persona con ID " + id + " no encontrada.");
            return createResponse(HttpStatus.BAD_REQUEST, messages, null);
        }

        personService.deletePerson(id);
        List<String> messages = List.of("Persona con ID " + id + " ha sido eliminada exitosamente.");
        return createResponse(HttpStatus.OK, messages, null);
    }

    private boolean exists(Integer id) {
        return personService.getPersonById(id) != null;
    }

    private <T> ResponseEntity<APIResponse<T>> createResponse(HttpStatus status, List<String> messages, T data) {
        APIResponse<T> response = new APIResponse<>(status.value(), messages, data);
        return ResponseEntity.status(status).body(response);
    }
}