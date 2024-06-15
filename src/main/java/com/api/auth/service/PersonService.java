package com.api.auth.service;

import com.api.auth.entity.Person;
import com.api.auth.repository.PersonRepository;
import com.api.auth.service.exception.DuplicateEmailException;
import com.api.auth.service.exception.InvalidPersonDataException;
import com.api.auth.service.exception.PersonNotFoundException;
import com.api.auth.utils.PersonValidator;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type Person service.
 */
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;
  private final PersonValidator personValidator;
  private final EmailService emailService;

  /**
   * Instantiates a new Person service.
   *
   * @param personRepository the person repository
   * @param personValidator  the person validator
   * @param emailService     the email service
   */
  @Autowired
  public PersonService(PersonRepository personRepository, PersonValidator personValidator,
      EmailService emailService) {
    this.personRepository = personRepository;
    this.personValidator = personValidator;
    this.emailService = emailService;
  }

  /**
   * Save person.
   *
   * @param person the person
   * @return the person
   * @throws InvalidPersonDataException the invalid person data exception
   */
  @Transactional
  public Person save(Person person) throws InvalidPersonDataException {
    personValidator.validatePersonData(person);
    validateEmail(person.getEmail());

    String hashPassword = new BCryptPasswordEncoder().encode(person.getPassword());
    person.setPassword(hashPassword);

    personRepository.save(person);
    emailService.sendWelcomeEmail(person);

    return person;
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Person> findAll() {
    return personRepository.findAll();
  }

  /**
   * Find by id person.
   *
   * @param id the id
   * @return the person
   * @throws PersonNotFoundException the person not found exception
   */
  public Person findById(Long id) throws PersonNotFoundException {
    return personRepository.findById(id)
        .orElseThrow(PersonNotFoundException::new);
  }

  private void validateEmail(String email) {
    if (personRepository.findByEmail(email).isPresent()) {
      throw new DuplicateEmailException();
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
  }
}
