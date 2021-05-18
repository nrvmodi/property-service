package com.speedhome.repository;

import com.speedhome.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface PropertyRepository
        extends JpaRepository<Property, UUID>, JpaSpecificationExecutor<Property> {

}
