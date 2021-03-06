/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tha
 */
public class PersonDTO {
    private long id;
    private List<PhoneDTO> phones;
    private String email;
    private String firstName;
    private String lastName;
    private AddressDTO address;
    private List<HobbyDTO> hobbies;

    public PersonDTO(List<PhoneDTO> phones, String email, String firstName, String lastName, AddressDTO address, List<HobbyDTO> hobbies) {
        this.phones = phones;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.hobbies = hobbies;
    }
    public PersonDTO(List<PhoneDTO> phones, String email, String firstName, String lastName, AddressDTO address) {
        this.phones = phones;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.hobbies = new ArrayList<>();
    }

    public static List<PersonDTO> getDtos(List<Person> persons) {
        List<PersonDTO> personDTOs = new ArrayList();
        persons.forEach(person -> personDTOs.add(new PersonDTO(person)));
        return personDTOs;
    }


    public PersonDTO(Person person) {
        if (person.getId() != null)
            this.id = person.getId();
        this.phones = PhoneDTO.getDtos(person.getPhones());
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.address = new AddressDTO(person.getAddress());
        this.hobbies = HobbyDTO.getDtos(person.getHobbies());
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<HobbyDTO> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<HobbyDTO> hobbies) {
        this.hobbies = hobbies;
    }
}
