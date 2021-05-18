package com.speedhome.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class PropertyDTO implements Serializable {
    private UUID id;

    private String name;

    private String address;

    private String city;

    private String country;

    private boolean deleted;
}
