package com.example.footballmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Long age;

    @NonNull
    private Long experience;

    @NonNull
    private String teamName;
}
