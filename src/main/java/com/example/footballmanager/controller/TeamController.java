package com.example.footballmanager.controller;

import com.example.footballmanager.dto.AddTeamDto;
import com.example.footballmanager.dto.PlayerDto;
import com.example.footballmanager.dto.TeamDto;
import com.example.footballmanager.dto.UpdateTeamDto;
import com.example.footballmanager.service.TeamService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * Controller for adding a new team.
     *
     * @param dto  {@link AddTeamDto};
     */
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addTeam(@RequestBody @Validated AddTeamDto dto) {
        teamService.saveTeam(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Controller to get the team by its id.
     *
     * @param id {@link Long};
     * @return {@link TeamDto}
     */
    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getTeam(id));
    }

    /**
     * Controller to get all teams.
     *
     * @return list of {@link TeamDto}
     */
    @GetMapping("/all")
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getAllTeams());
    }

    /**
     * Controller to get all players for the certain team.
     *
     * @param teamId {@link Long};
     * @return list of {@link PlayerDto}
     */
    @GetMapping("/{teamId}/players")
    public ResponseEntity<List<PlayerDto>> getAllPlayers(@PathVariable Long teamId) {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getAllPlayersForTheTeam(teamId));
    }

    /**
     * Controller for updating the team.
     *
     * @param id {@link Long};
     * @param dto {@link UpdateTeamDto};
     * @return {@link TeamDto};
     */
    @PatchMapping("/update/{id}")
    public ResponseEntity<TeamDto> updateTeam(@PathVariable Long id, @RequestBody @Validated UpdateTeamDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.updateTeam(id, dto));
    }

    /**
     * Controller for deleting the team.
     *
     * @param id {@link Long};
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
