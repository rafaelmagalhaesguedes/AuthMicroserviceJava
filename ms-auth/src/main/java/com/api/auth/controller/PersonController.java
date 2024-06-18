package com.api.auth.controller;

import com.api.auth.controller.dto.PersonCreationDto;
import com.api.auth.controller.dto.PersonDto;
import com.api.auth.entity.Person;
import com.api.auth.service.PersonService;
import com.api.auth.service.exception.InvalidPersonDataException;
import com.api.auth.service.exception.PersonNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing persons.
 */
@RestController
@RequestMapping("/persons")
@Validated
public class PersonController {

  private final PersonService personService;

  /**
   * Instantiates a new Person controller.
   *
   * @param personService the person service
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create a new person.
   *
   * @param personCreationDto the person creation dto
   * @return the created person dto
   * @throws InvalidPersonDataException if the person data is invalid
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createPerson(@RequestBody @Valid PersonCreationDto personCreationDto)
      throws InvalidPersonDataException {
    Person person = personCreationDto.toEntity();
    return PersonDto.fromEntity(personService.save(person));
  }

  /**
   * Get all persons.
   *
   * @return the list of persons
   */
  @GetMapping
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  public List<PersonDto> findAllPersons() {
    List<Person> personList = personService.findAll();
    return personList.stream()
        .map(PersonDto::fromEntity)
        .toList();
  }

  /**
   * Get person by ID.
   *
   * @param id the person ID
   * @return the person dto
   * @throws PersonNotFoundException if the person is not found
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  public PersonDto findPersonById(@PathVariable UUID id) throws PersonNotFoundException {
    return PersonDto.fromEntity(personService.findById(id));
  }
}
