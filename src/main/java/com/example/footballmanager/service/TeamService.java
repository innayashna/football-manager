package com.example.footballmanager.service;

import com.example.footballmanager.dto.AddPlayerDto;
import com.example.footballmanager.dto.AddTeamDto;
import com.example.footballmanager.dto.PlayerDto;
import com.example.footballmanager.dto.TeamDto;
import com.example.footballmanager.dto.UpdateTeamDto;

import java.util.List;

public interface TeamService {

    /**
     * Method saves a new team.
     *
     * @param dto {@link AddTeamDto};
     */
    void saveTeam(AddTeamDto dto);

    /**
     * Method to get the team by its id.
     *
     * @param id {@link Long};
     */
    TeamDto getTeam(Long id);

    /**
     * Method to get all teams.
     *
     */
    List<TeamDto> getAllTeams();

    /**
     * Method to get all players for the certain team.
     *
     * @param teamId {@link Long};
     *
     * @return list of {@link AddPlayerDto};
     */
    List<PlayerDto> getAllPlayersForTheTeam(Long teamId);

    /**
     * Method updates the team's info.
     *
     * @param id {@link Long};
     * @param dto {@link UpdateTeamDto};
     */
    TeamDto updateTeam(Long id, UpdateTeamDto dto);

    /**
     * Method deletes the team.
     *
     * @param id {@link Long};
     */
    void deleteTeam(Long id);
}
