package com.api.auth;

import com.api.auth.entities.Person;
import com.api.auth.repositories.PersonRepository;
import com.api.auth.securities.Role;
import com.api.auth.services.PersonService;
import com.api.auth.services.exception.InvalidPersonDataException;
import com.api.auth.services.exception.PersonNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonServiceTest {

  @Autowired
  private PersonService personService;

  @MockBean
  private PersonRepository personRepository;

  @Test
  public void testPersonCreation() throws InvalidPersonDataException {
    // Given
    Person person = new Person();
    person.setId(UUID.randomUUID());
    person.setFullName("Rafael Guedes");
    person.setEmail("rafa@email.com");
    person.setPassword("mysecretkey");
    person.setRole(Role.ADMIN);

    Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

    // When
    Person createdPerson = personService.save(person);

    // Then
    assertEquals(person.getId(), createdPerson.getId());
    assertEquals(person.getFullName(), createdPerson.getFullName());
    assertEquals(person.getEmail(), createdPerson.getEmail());
    assertEquals(person.getPassword(), createdPerson.getPassword());
    assertEquals(person.getRole(), createdPerson.getRole());
  }

  @Test
  public void testFindPersonById() throws PersonNotFoundException {
    // Given
    UUID id = UUID.randomUUID();
    Person person = new Person();
    person.setId(id);
    person.setFullName("Rafael Guedes");
    person.setEmail("rafa@email.com");
    person.setPassword("mysecretkey");
    person.setRole(Role.ADMIN);

    Mockito.when(personRepository.findById(id)).thenReturn(Optional.of(person));

    // When
    Person foundPerson = personService.findById(id);

    // Then
    assertEquals(person.getId(), foundPerson.getId());
    assertEquals(person.getFullName(), foundPerson.getFullName());
    assertEquals(person.getEmail(), foundPerson.getEmail());
    assertEquals(person.getPassword(), foundPerson.getPassword());
    assertEquals(person.getRole(), foundPerson.getRole());

    // Verify repository method was called with the correct UUID
    Mockito.verify(personRepository).findById(id);
  }

  @Test
  public void testFindAllPersons() {
    // Given
    List<Person> persons = new ArrayList<>();
    persons.add(createPerson(UUID.randomUUID(), "John Doe", "john@example.com", "password", Role.USER));
    persons.add(createPerson(UUID.randomUUID(), "Jane Smith", "jane@example.com", "password", Role.USER));

    int pageNumber = 0;
    int pageSize = 2;
    PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
    Page<Person> page = new PageImpl<>(persons, pageRequest, persons.size());

    Mockito.when(personRepository.findAll(pageRequest)).thenReturn(page);

    // When
    List<Person> foundPersons = personService.findAll(pageNumber, pageSize);

    // Then
    assertEquals(persons.size(), foundPersons.size());
    assertEquals(persons.get(0).getId(), foundPersons.get(0).getId());
    assertEquals(persons.get(1).getId(), foundPersons.get(1).getId());
    assertEquals(persons.get(0).getFullName(), foundPersons.get(0).getFullName());
    assertEquals(persons.get(1).getFullName(), foundPersons.get(1).getFullName());
    assertEquals(persons.get(0).getEmail(), foundPersons.get(0).getEmail());
    assertEquals(persons.get(1).getEmail(), foundPersons.get(1).getEmail());
    assertEquals(persons.get(0).getRole(), foundPersons.get(0).getRole());
    assertEquals(persons.get(1).getRole(), foundPersons.get(1).getRole());
  }
  @Test
  public void testFindAllPersonsEmptyList() {
    // Given
    List<Person> persons = new ArrayList<>();
    int pageNumber = 0;
    int pageSize = 2;
    PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
    Page<Person> page = new PageImpl<>(persons, pageRequest, persons.size());

    Mockito.when(personRepository.findAll(pageRequest)).thenReturn(page);

    // When
    List<Person> foundPersons = personService.findAll(pageNumber, pageSize);

    // Then
    assertEquals(persons.size(), foundPersons.size());
  }

  private Person createPerson(UUID id, String fullName, String email, String password, Role role) {
    Person person = new Person();
    person.setId(id);
    person.setFullName(fullName);
    person.setEmail(email);
    person.setPassword(password);
    person.setRole(role);
    return person;
  }
}
