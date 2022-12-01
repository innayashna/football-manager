package com.example.footballmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamDto {

    private String name;

    @Min(0)
    private Long account;

    @Min(0)
    @Max(10)
    private Long transferCommission;
}
