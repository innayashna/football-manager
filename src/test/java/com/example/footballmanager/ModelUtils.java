package com.example.footballmanager;

import com.example.footballmanager.dto.AddTeamDto;
import com.example.footballmanager.dto.AddPlayerDto;
import com.example.footballmanager.dto.UpdatePlayerDto;
import com.example.footballmanager.dto.UpdateTeamDto;
import com.example.footballmanager.entity.Player;
import com.example.footballmanager.entity.Team;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ModelUtils {

    public static AddPlayerDto getPlayerDto() {
        AddPlayerDto dto = new AddPlayerDto();
        dto.setName("Inna");
        dto.setBirthDate(LocalDate.parse("2004-02-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dto.setCareerStart(LocalDate.parse("2019-09-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dto.setTeamId(1L);
        return dto;
    }

    public static Player getPlayer() {
        Player player = new Player();
        player.setId(1L);
        player.setName("Inna");
        player.setBirthDate(LocalDate.parse("2004-02-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        player.setCareerStart(LocalDate.parse("2019-09-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        player.setTeam(getTeam());
        return player;
    }

    public static UpdatePlayerDto getUpdatePlayerDto() {
        UpdatePlayerDto dto = new UpdatePlayerDto();
        dto.setBirthDate(LocalDate.parse("2003-02-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return dto;
    }

    public static Team getTeam() {
        List<Player> players = new ArrayList<>();
        Team team = new Team();
        team.setId(1L);
        team.setName("Team");
        team.setAccount(1000000000L);
        team.setTransferCommission(2L);
        team.setPlayers(players);
        return team;
    }

    public static Team getNewTeam() {
        List<Player> players = new ArrayList<>();
        Team team = new Team();
        team.setId(2L);
        team.setName("Team");
        team.setAccount(1000000000L);
        team.setTransferCommission(2L);
        team.setPlayers(players);
        return team;
    }

    public static AddTeamDto getAddTeamDto() {
        AddTeamDto dto = new AddTeamDto();
        dto.setName("Team");
        dto.setAccount(1000000000L);
        dto.setTransferCommission(2L);
        return dto;
    }

    public static UpdateTeamDto getUpdateTeamDto() {
        UpdateTeamDto dto = new UpdateTeamDto();
        dto.setName("Name");
        return dto;
    }
}
