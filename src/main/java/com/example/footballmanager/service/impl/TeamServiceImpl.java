package com.example.footballmanager.service.impl;

import com.example.footballmanager.dto.AddTeamDto;
import com.example.footballmanager.dto.PlayerDto;
import com.example.footballmanager.dto.TeamDto;
import com.example.footballmanager.dto.UpdateTeamDto;
import com.example.footballmanager.entity.Player;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.exeption.TeamNotFoundException;
import com.example.footballmanager.repository.TeamRepository;
import com.example.footballmanager.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.footballmanager.exeption.constants.ErrorMessageConstants.TEAM_WAS_NOT_FOUND;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTeam(AddTeamDto dto) {
        Team team = modelMapper.map(dto, Team.class);
        teamRepository.save(team);
    }

    @Override
    public TeamDto getTeam(Long id) {
        return modelMapper.map(findTeamById(id), TeamDto.class);
    }

    @Override
    public List<TeamDto> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(team -> modelMapper.map(team, TeamDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PlayerDto> getAllPlayersForTheTeam(Long teamId) {
        Team team = findTeamById(teamId);
        List<Player> players = team.getPlayers();
        return players.stream().map(player -> modelMapper.map(player, PlayerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto updateTeam(Long id, UpdateTeamDto dto) {
        Team team = findTeamById(id);
        Optional.ofNullable(dto.getName()).ifPresent(team::setName);
        Optional.ofNullable(dto.getAccount()).ifPresent(team::setAccount);
        Optional.ofNullable(dto.getTransferCommission()).ifPresent(team::setTransferCommission);
        teamRepository.save(team);
        return modelMapper.map(team, TeamDto.class);
    }

    @Override
    public void deleteTeam(Long id) {
        Team team = findTeamById(id);
        teamRepository.deleteById(team.getId());
    }

    private Team findTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(
                () -> new TeamNotFoundException(String.format(TEAM_WAS_NOT_FOUND, id)));
    }
}
