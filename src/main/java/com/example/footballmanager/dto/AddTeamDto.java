package com.example.footballmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTeamDto {

    @NotNull
    private String name;

    @Min(0)
    @NotNull
    private Long account;

    @Min(0)
    @Max(10)
    @NotNull
    private Long transferCommission;
}
