package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {
    private Long id;
    private String name;
}