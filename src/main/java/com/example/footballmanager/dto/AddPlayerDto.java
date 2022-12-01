package com.example.footballmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPlayerDto {

    @NonNull
    private String name;

    @Min(0)
    @NonNull
    private Long age;

    @Min(0)
    @NonNull
    private Long experience;

    @NonNull
    private Long teamId;
}
