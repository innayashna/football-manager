package com.example.footballmanager.service.impl;

import com.example.footballmanager.ModelUtils;
import com.example.footballmanager.dto.AddPlayerDto;
import com.example.footballmanager.dto.UpdatePlayerDto;
import com.example.footballmanager.entity.Player;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.exeption.InsufficientFundException;
import com.example.footballmanager.repository.PlayerRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void savePlayer() {
        AddPlayerDto dto = ModelUtils.getPlayerDto();
        Player player = ModelUtils.getPlayer();

        when(modelMapper.map(dto, Player.class)).thenReturn(player);

        playerService.savePlayer(dto);
        verify(modelMapper).map(dto, Player.class);
        verify(playerRepository).save(player);
    }

    @Test
    void getPlayer() {
        Player player = ModelUtils.getPlayer();
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        playerService.getPlayer(1L);
        verify(playerRepository).findById(1L);
    }

    @Test
    void getAllPlayers() {
        List<Player> players = List.of(ModelUtils.getPlayer());
        when(playerRepository.findAll()).thenReturn(players);
        playerService.getAllPlayers();
        verify(playerRepository).findAll();
    }

    @Test
    void updatePlayer() {
        Player player = ModelUtils.getPlayer();
        UpdatePlayerDto dto = ModelUtils.getUpdatePlayerDto();
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        playerService.updatePlayer(1L, dto);
        verify(playerRepository).save(player);
        assertEquals(player.getAge(), dto.getAge());
    }

    @Test
    void deletePlayer() {
        Player player = ModelUtils.getPlayer();
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        playerService.deletePlayer(1L);
        verify(playerRepository).deleteById(1L);
    }

    @Test
    void transferPlayer() {
        Player player = ModelUtils.getPlayer();
        Team oldTeam = ModelUtils.getTeam();
        Team newTeam = ModelUtils.getNewTeam();

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        when(teamRepository.findById(1L)).thenReturn(Optional.of(oldTeam));
        when(teamRepository.findById(2L)).thenReturn(Optional.of(newTeam));

        playerService.transferPlayer(1L, 2L);
        verify(playerRepository).findById(1L);
        verify(teamRepository).findById(1L);
        verify(teamRepository).findById(2L);
    }

    @Test
    void transferPlayerThrowException() {
        Player player = ModelUtils.getPlayer();
        Team oldTeam = ModelUtils.getTeam();
        Team newTeam = ModelUtils.getNewTeam();
        newTeam.setAccount(1L);

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        when(teamRepository.findById(1L)).thenReturn(Optional.of(oldTeam));
        when(teamRepository.findById(2L)).thenReturn(Optional.of(newTeam));

        assertThrows(InsufficientFundException.class, () -> playerService.transferPlayer(1L, 2L));
    }
}