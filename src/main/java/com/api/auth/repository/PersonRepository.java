package com.api.auth.repository;

import com.api.auth.entity.Person;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Person repository.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

  /**
   * Find by username optional.
   *
   * @param username the username
   * @return the optional
   */
  Optional<Person> findByUsername(String username);

  /**
   * Find a person by email.
   *
   * @param email the email of the person
   * @return an Optional containing the person if found, or empty otherwise
   */
  Optional<Person> findByEmail(String email);
}
