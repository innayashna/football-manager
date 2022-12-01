package com.example.footballmanager.service.mapping;

import com.example.footballmanager.dto.AddPlayerDto;
import com.example.footballmanager.entity.Player;
import com.example.footballmanager.exeption.TeamNotFoundException;
import com.example.footballmanager.repository.TeamRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.footballmanager.exeption.constants.ErrorMessageConstants.TEAM_WAS_NOT_FOUND;

/**
 * Class that used by {@link ModelMapper} to map
 * {@link AddPlayerDto} into
 * {@link com.example.footballmanager.entity.Player}.
 */
@Component
public class AddPlayerDtoMapper extends AbstractConverter<AddPlayerDto, Player> {

    private final TeamRepository teamRepository;

    @Autowired
    public AddPlayerDtoMapper(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    protected Player convert(AddPlayerDto dto) {
        Player player = new Player();
        player.setAge(dto.getAge());
        player.setName(dto.getName());
        player.setExperience(dto.getExperience());
        player.setTeam(teamRepository.findById(dto.getTeamId()).orElseThrow(
                () -> new TeamNotFoundException(TEAM_WAS_NOT_FOUND + dto.getTeamId())));
        return player;
    }
}
