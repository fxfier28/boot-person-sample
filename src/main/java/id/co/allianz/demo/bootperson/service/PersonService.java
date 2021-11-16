package id.co.allianz.demo.bootperson.service;

import id.co.allianz.demo.bootperson.model.Person;

public interface PersonService {

    Person findById(int id);

    Person create(Person person);

    void update(Person person, int id);

    void deleteById(int id);

    Integer cekDob(int id);

}
