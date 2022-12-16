package com.example.footballmanager.config;

import com.example.footballmanager.entity.Player;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.repository.PlayerRepository;
import com.example.footballmanager.repository.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class LoadDatabaseConfiguration {

    @Bean
    CommandLineRunner initDatabase(PlayerRepository playerRepository, TeamRepository teamRepository) {
        return args -> {
            Team team1 = new Team();
            team1.setName("Ukraine-1");
            team1.setTransferCommission(5L);
            team1.setAccount(100000L);

            Team team2 = new Team();
            team2.setName("Ukraine-2");
            team2.setTransferCommission(1L);
            team2.setAccount(10000L);

            team1 = teamRepository.save(team1);
            team2 = teamRepository.save(team2);

            Player player1 = new Player();
            player1.setName("Inna");
            player1.setCareerStart(LocalDate.parse("2019-04-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            player1.setBirthDate(LocalDate.parse("2001-08-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            player1.setTeam(team1);
            playerRepository.save(player1);

            Player player2 = new Player();
            player2.setName("Philip");
            player2.setCareerStart(LocalDate.parse("2018-04-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            player2.setBirthDate(LocalDate.parse("1997-04-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            player2.setTeam(team1);
            playerRepository.save(player2);

            Player player3 = new Player();
            player3.setName("Max");
            player3.setCareerStart(LocalDate.parse("2019-09-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            player3.setBirthDate(LocalDate.parse("2004-02-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            player3.setTeam(team2);
            playerRepository.save(player3);

            Player player4 = new Player();
            player4.setName("Alex");
            player4.setCareerStart(LocalDate.parse("2020-06-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            player4.setBirthDate(LocalDate.parse("1999-05-13", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            player4.setTeam(team2);
            playerRepository.save(player4);
        };
    }
}
