package com.kodilla.ecommercee.dto;

import java.math.BigInteger;

public class GroupDto {
    private BigInteger id;
    private String name;

    public GroupDto(BigInteger id, String name){
        this.id = id;
        this.name = name;
    }

    public BigInteger getId(){ return id; }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName(){ return name;}

    public void setName(String name){ this.name = name; }
}
