package com.example.footballmanager.service.mapping;

import com.example.footballmanager.dto.TeamDto;
import com.example.footballmanager.entity.Team;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Class that used by {@link ModelMapper} to map
 * {@link com.example.footballmanager.entity.Team} into
 * {@link com.example.footballmanager.dto.TeamDto}.
 */
@Component
public class TeamMapper extends AbstractConverter<Team, TeamDto> {

    private final PlayerDtoMapper playerMapper;

    @Autowired
    public TeamMapper(PlayerDtoMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    @Override
    protected TeamDto convert(Team team) {
        TeamDto dto = new TeamDto();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setAccount(team.getAccount());
        dto.setTransferCommission(team.getTransferCommission());
        dto.setPlayers(team.getPlayers().stream()
                .map(playerMapper::convert)
                .collect(Collectors.toList()));
        return dto;
    }
}
