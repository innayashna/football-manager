package com.example.footballmanager.service.mapping;

import com.example.footballmanager.dto.PlayerDto;
import com.example.footballmanager.entity.Player;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Class that used by {@link ModelMapper} to map
 * {@link PlayerDto} into
 * {@link com.example.footballmanager.entity.Player}.
 */
@Component
public class PlayerDtoMapper extends AbstractConverter<Player, PlayerDto> {

    @Override
    protected PlayerDto convert(Player player) {
        PlayerDto dto = new PlayerDto();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setAge(player.getAge());
        dto.setExperience(player.getExperience());
        dto.setTeamName(player.getTeam().getName());
        return dto;
    }
}