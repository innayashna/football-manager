package com.example.footballmanager.service;

import com.example.footballmanager.dto.AddPlayerDto;
import com.example.footballmanager.dto.PlayerDto;
import com.example.footballmanager.dto.UpdatePlayerDto;
import com.example.footballmanager.exeption.InsufficientFundException;

import java.util.List;

public interface PlayerService {

    /**
     * Method saves a new player to the team.
     *
     * @param dto {@link AddPlayerDto};
     */
    void savePlayer(AddPlayerDto dto);

    /**
     * Method to get the player by its id.
     *
     * @param id {@link Long};
     *
     * @return {@link AddPlayerDto};
     */
    PlayerDto getPlayer(Long id);

    /**
     * Method to get all players.
     *
     */
    List<PlayerDto> getAllPlayers();

    /**
     * Method updates the player's info.
     *
     * @param id {@link Long};
     * @param dto {@link AddPlayerDto};
     */
    AddPlayerDto updatePlayer(Long id, UpdatePlayerDto dto);

    /**
     * Method deletes the player.
     *
     * @param id {@link Long};
     */
    void deletePlayer(Long id);

    /**
     * Method transfers the player from one team to another.
     *
     * @param playerId {@link Long};
     * @param newTeamId {@link Long};
     */
    void transferPlayer(Long playerId, Long newTeamId) throws InsufficientFundException;
}
