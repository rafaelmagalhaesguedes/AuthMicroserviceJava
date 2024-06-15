package com.api.auth.controller;

import com.api.auth.controller.dto.PersonCreationDto;
import com.api.auth.controller.dto.PersonDto;
import com.api.auth.entity.Person;
import com.api.auth.service.PersonService;
import com.api.auth.service.exception.InvalidPersonDataException;
import com.api.auth.service.exception.PersonNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
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
 * The type Person controller.
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
   * Create person dto.
   *
   * @param personCreationDto the person creation dto
   * @return the person dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createPerson(@Valid @RequestBody PersonCreationDto personCreationDto)
      throws InvalidPersonDataException {
    Person person = personCreationDto.toEntity();
    return PersonDto.fromEntity(personService.save(person));
  }

  /**
   * Find all persons list.
   *
   * @return the list
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
   * Find person by id person dto.
   *
   * @param id the id
   * @return the person dto
   * @throws PersonNotFoundException the person not found exception
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  public PersonDto findPersonById(@PathVariable Long id) throws PersonNotFoundException {
    return PersonDto.fromEntity(personService.findById(id));
  }
}
