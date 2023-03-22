package com.epam.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dominik_Janiga
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
