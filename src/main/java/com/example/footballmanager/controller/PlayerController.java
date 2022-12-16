package com.example.footballmanager.controller;

import com.example.footballmanager.dto.AddPlayerDto;
import com.example.footballmanager.dto.PlayerDto;
import com.example.footballmanager.dto.UpdatePlayerDto;
import com.example.footballmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Controller for adding a new player.
     *
     * @param dto {@link AddPlayerDto};
     */
    @PostMapping("/")
    public ResponseEntity<HttpStatus> addPlayer(@RequestBody @Validated AddPlayerDto dto) {
        playerService.savePlayer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Controller to get the player by its id.
     *
     * @param id {@link Long};
     * @return {@link AddPlayerDto};
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getPlayer(id));
    }

    /**
     * Controller to get all players.
     *
     * @return list of {@link PlayerDto}
     */
    @GetMapping("/")
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getAllPlayers());
    }

    /**
     * Controller for updating the player.
     *
     * @param id {@link Long};
     * @param dto {@link AddPlayerDto};
     * @return {@link AddPlayerDto};
     */
    @PatchMapping("/{id}")
    public ResponseEntity<AddPlayerDto> updatePlayer(@PathVariable Long id, @RequestBody @Validated UpdatePlayerDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.updatePlayer(id, dto));
    }

    /**
     * Controller for deleting the player.
     *
     * @param id {@link Long};
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Controller transferring the player from one team to another.
     *
     * @param playerId {@link Long};
     * @param newTeamId {@link Long};
     */
    @PatchMapping("/{playerId}/transfer")
    public ResponseEntity<HttpStatus> transferPlayer(@PathVariable Long playerId,
                                                     @RequestParam Long newTeamId) {
        playerService.transferPlayer(playerId, newTeamId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
