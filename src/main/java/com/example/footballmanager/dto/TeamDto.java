package com.example.footballmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long account;

    @NotNull
    private Long transferCommission;

    private List<PlayerDto> players;
}
