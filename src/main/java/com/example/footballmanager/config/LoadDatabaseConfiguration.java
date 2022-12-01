package com.example.footballmanager.config;

import com.example.footballmanager.entity.Player;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.repository.PlayerRepository;
import com.example.footballmanager.repository.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
            player1.setExperience(1L);
            player1.setAge(21L);
            player1.setTeam(team1);
            playerRepository.save(player1);

            Player player2 = new Player();
            player2.setName("Philip");
            player2.setExperience(2L);
            player2.setAge(22L);
            player2.setTeam(team1);
            playerRepository.save(player2);

            Player player3 = new Player();
            player3.setName("Max");
            player3.setExperience(1L);
            player3.setAge(18L);
            player3.setTeam(team2);
            playerRepository.save(player3);

            Player player4 = new Player();
            player4.setName("Alex");
            player4.setExperience(6L);
            player4.setAge(25L);
            player4.setTeam(team2);
            playerRepository.save(player4);
        };
    }
}
