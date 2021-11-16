package id.co.allianz.demo.bootperson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.allianz.demo.bootperson.model.Person;
import id.co.allianz.demo.bootperson.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person findById(int id) {
        return personRepository.findById(id).get();
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void update(Person person, int id) {
        Person updatePerson = personRepository.findById(id).get();
        updatePerson.setFullname(person.getFullname());
        updatePerson.setDob(person.getDob());
        personRepository.save(updatePerson);
    }

    @Override
    public void deleteById(int id) {
        personRepository.deleteById(id);
    }

    @Override
    public Integer cekDob(int id) {
        return personRepository.calcdob(id);
    }

}
