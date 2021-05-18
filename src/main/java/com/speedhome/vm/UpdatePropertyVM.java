package com.speedhome.vm;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdatePropertyVM implements Serializable {

    private static final long serialVersionUID = -1l;

    private String name;
    private String address;
    private String city;
    private String country;

}
