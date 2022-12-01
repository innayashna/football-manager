package com.example.footballmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTeamDto {

    @NonNull
    private String name;

    @Min(0)
    @NonNull
    private Long account;

    @Min(0)
    @Max(10)
    @NonNull
    private Long transferCommission;
}
