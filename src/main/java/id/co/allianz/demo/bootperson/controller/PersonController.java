package id.co.allianz.demo.bootperson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.allianz.demo.bootperson.model.Person;
import id.co.allianz.demo.bootperson.service.PersonService;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        return personService.findById(id);
    }

    @GetMapping("/person/dob/{id}")
    public Integer cekDob(@PathVariable("id") int id) {
        return personService.cekDob(id);
    }

    @PostMapping("/person")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        person = personService.create(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PutMapping("/person/{id}")
    public void updatePerson(@RequestBody Person newPerson, @PathVariable int id) {
        personService.update(newPerson, id);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        personService.deleteById(id);
    }
}
