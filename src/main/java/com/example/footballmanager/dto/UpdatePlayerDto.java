package com.example.footballmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlayerDto {

    private String name;

    @Min(1)
    private Long age;

    @Min(0)
    private Long experience;
}
