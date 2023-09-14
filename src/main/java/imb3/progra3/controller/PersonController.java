package imb3.progra3.controller;

import java.util.*;
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
import imb3.progra3.entity.Person;
import imb3.progra3.service.IPersonService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<APIResponse<List<Person>>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        if (persons.isEmpty()) {
            APIResponse<List<Person>> response = new APIResponse<>(200, null, personService.getAllPersons());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            APIResponse<List<Person>> response = new APIResponse<>(200, null, persons);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<APIResponse<Person>> getPersonById(@PathVariable Integer id) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            List<String> messages = List.of("Person with id " + id + " not found.");
            APIResponse<Person> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            APIResponse<Person> response = new APIResponse<>(HttpStatus.OK.value(), null, person);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PostMapping("/persons")
    public ResponseEntity<APIResponse<Person>> createPerson(@RequestBody Person person) {
        if (person.getId() != null) {
            Person existingPerson = personService.getPersonById(person.getId());
            if (existingPerson != null) {
                List<String> messages = List.of("Person with id " + person.getId() + " already exists.", "Use PUT to update.");
                APIResponse<Person> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), messages, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }

        Person createdPerson = personService.createPerson(person);
        APIResponse<Person> response = new APIResponse<>(HttpStatus.CREATED.value(), null, createdPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<APIResponse<Person>> editPerson(@PathVariable Integer id, @RequestBody Person person) {
        Person existingPerson = personService.getPersonById(id);
        if (existingPerson == null) {
            List<String> messages = List.of("Person with id " + id + " not found.", "Use POST to create a new person.");
            APIResponse<Person> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Person updatedPerson = personService.editPerson(id, person);
        APIResponse<Person> response = new APIResponse<>(HttpStatus.OK.value(), null, updatedPerson);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<APIResponse<String>> deletePerson(@PathVariable Integer id) {
        Person existingPerson = personService.getPersonById(id);
        if (existingPerson == null) {
            List<String> messages = List.of("Person with id " + id + " not found.");
            APIResponse<String> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        personService.deletePerson(id);
        List<String> messages = List.of("Person with id " + id + " has been deleted successfully.");
        APIResponse<String> response = new APIResponse<>(HttpStatus.OK.value(), messages, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}