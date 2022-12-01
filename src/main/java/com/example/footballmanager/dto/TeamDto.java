package com.example.footballmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Long account;

    @NonNull
    private Long transferCommission;

    private List<PlayerDto> players;
}
