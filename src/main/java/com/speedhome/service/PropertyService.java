package com.speedhome.service;

import com.speedhome.domain.Property;
import com.speedhome.repository.PropertyRepository;
import com.speedhome.service.mapper.PropertyMapper;
import com.speedhome.vm.UpdatePropertyVM;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public PropertyService(PropertyRepository propertyRepository,
                           PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
    }

    public Property isExist(UUID id) {

        Optional<Property> propertyOptional = propertyRepository.findById(id);

        Property property = null;
        if (propertyOptional.isPresent()) {

            property = propertyOptional.get();
        } else {

            throw new InternalException(String.format("Property %s not found", id));
        }

        return property;
    }

    public Property save(Property property) {

        Property save = propertyRepository.save(property);
        log.info("New Property {} created successfully.", save);
        return save;
    }

    @Transactional(readOnly = true)
    public Page<Property> getAll(Specification<Property> specification, Pageable pageable) {

        return propertyRepository.findAll(specification, pageable);
    }


    @Transactional(readOnly = true)
    public List<Property> getAll(Specification<Property> specification) {

        return propertyRepository.findAll(specification);
    }

    public Property update(UUID id, UpdatePropertyVM updatePropertyVM) {
        Property property = isExist(id);

        property.setAddress(updatePropertyVM.getAddress());
        property.setCity(updatePropertyVM.getCity());
        property.setCountry(updatePropertyVM.getCountry());

        propertyRepository.save(property);
        return property;
    }

    public void delete(UUID id) {
        propertyRepository.deleteById(id);
    }

    public Page<Property> getAll(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }
}
