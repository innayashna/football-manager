package com.example.footballmanager.service.mapping;

import com.example.footballmanager.dto.AddTeamDto;
import com.example.footballmanager.entity.Team;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Class that used by {@link ModelMapper} to map
 * {@link com.example.footballmanager.dto.AddTeamDto} into
 * {@link com.example.footballmanager.entity.Team}.
 */
@Component
public class AddTeamDtoMapper extends AbstractConverter<AddTeamDto, Team> {

    @Override
    protected Team convert(AddTeamDto dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setAccount(dto.getAccount());
        team.setTransferCommission(dto.getTransferCommission());
        return team;
    }
}
