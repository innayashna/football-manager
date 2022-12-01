package com.example.footballmanager.service.mapping;

import com.example.footballmanager.dto.AddPlayerDto;
import com.example.footballmanager.entity.Player;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Class that used by {@link ModelMapper} to map
 * {@link com.example.footballmanager.entity.Player} into
 * {@link AddPlayerDto}.
 */
@Component
public class PlayerMapper extends AbstractConverter<Player, AddPlayerDto> {

    @Override
    protected AddPlayerDto convert(Player player) {
        AddPlayerDto dto = new AddPlayerDto();
        dto.setName(player.getName());
        dto.setAge(player.getAge());
        dto.setExperience(player.getExperience());
        dto.setTeamId(player.getTeam().getId());
        return dto;
    }
}
