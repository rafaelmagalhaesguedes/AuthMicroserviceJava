package com.email.repositories;

import com.email.entities.Email;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Email repository.
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {

}
