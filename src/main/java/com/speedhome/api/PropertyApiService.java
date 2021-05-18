package com.speedhome.api;

import com.speedhome.domain.Property;
import com.speedhome.dto.PropertyDTO;
import com.speedhome.repository.PropertyRepository;
import com.speedhome.service.PropertyService;
import com.speedhome.service.mapper.PropertyMapper;
import com.speedhome.specification.MatrixSpecificationsBuilder;
import com.speedhome.vm.NewPropertyVM;
import com.speedhome.vm.UpdatePropertyVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class PropertyApiService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    @Autowired
    private PropertyService propertyService;

    public PropertyApiService(PropertyRepository propertyRepository,
                              PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
    }

    public PropertyDTO addProperty(NewPropertyVM newPropertyVM) {

        Property target = new Property();
        BeanUtils.copyProperties(newPropertyVM, target);
        Property save = propertyService.save(target);
        log.info("New Property {} created successfully.", save);
        return propertyMapper.propertyToPropertyDTO(save);
    }

    public PropertyDTO update(UUID id, UpdatePropertyVM propertyDTO) {
        return propertyMapper.propertyToPropertyDTO(propertyService.update(id, propertyDTO));
    }

    public PropertyDTO getProperty(UUID id) {
        return propertyMapper.propertyToPropertyDTO(propertyService.isExist(id));
    }

    public void delete(UUID id) {
        propertyService.delete(id);
    }

    public Page<PropertyDTO> getAll(Map<String, List<Object>> matrixVars, Pageable pageable) {
        return propertyService.getAll(new MatrixSpecificationsBuilder<Property>(matrixVars, Property.class).build(), pageable)
                .map(p -> propertyMapper.propertyToPropertyDTO(p));
    }

    public Page<PropertyDTO> getAll(Pageable pageable) {
        return propertyService.getAll(pageable).map(p -> propertyMapper.propertyToPropertyDTO(p));
    }
}
