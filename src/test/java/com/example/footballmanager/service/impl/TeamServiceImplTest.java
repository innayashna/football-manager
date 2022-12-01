package com.example.footballmanager.service.impl;

import com.example.footballmanager.ModelUtils;
import com.example.footballmanager.dto.AddTeamDto;
import com.example.footballmanager.dto.UpdateTeamDto;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void saveTeam() {
        AddTeamDto dto = ModelUtils.getAddTeamDto();
        Team team = ModelUtils.getTeam();

        when(modelMapper.map(dto, Team.class)).thenReturn(team);

        teamService.saveTeam(dto);
        verify(modelMapper).map(dto, Team.class);
        verify(teamRepository).save(team);
    }

    @Test
    void getTeam() {
        Team team = ModelUtils.getTeam();
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        teamService.getTeam(1L);
        verify(teamRepository).findById(1L);
    }

    @Test
    void getAllTeams() {
        List<Team> teams = List.of(ModelUtils.getTeam());
        when(teamRepository.findAll()).thenReturn(teams);
        teamService.getAllTeams();
        verify(teamRepository).findAll();
    }

    @Test
    void getAllPlayersForTheTeam() {
        Team team = ModelUtils.getTeam();
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        teamService.getAllPlayersForTheTeam(1L);
        verify(teamRepository).findById(1L);
    }

    @Test
    void updateTeam() {
        Team team = ModelUtils.getTeam();
        UpdateTeamDto dto = ModelUtils.getUpdateTeamDto();
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        teamService.updateTeam(1L, dto);
        verify(teamRepository).save(team);
        assertEquals(team.getName(), dto.getName());
    }

    @Test
    void deleteTeam() {
        Team team = ModelUtils.getTeam();
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        teamService.deleteTeam(1L);
        verify(teamRepository).deleteById(1L);
    }
}