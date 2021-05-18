package com.speedhome.service.mapper;

import com.speedhome.domain.Property;
import com.speedhome.dto.PropertyDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PropertyMapper {

    public PropertyDTO propertyToPropertyDTO(Property property) {
        PropertyDTO dto = new PropertyDTO();
        BeanUtils.copyProperties(property, dto);
        return dto;
    }

    public List<PropertyDTO> propertiesToPropertyDTOs(List<Property> userQuotaRenewHistories) {
        return userQuotaRenewHistories.stream()
                .filter(Objects::nonNull)
                .map(this::propertyToPropertyDTO)
                .collect(Collectors.toList());
    }
}
