package com.example.footballmanager.service.impl;

import com.example.footballmanager.dto.AddPlayerDto;
import com.example.footballmanager.dto.PlayerDto;
import com.example.footballmanager.dto.UpdatePlayerDto;
import com.example.footballmanager.entity.Player;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.exeption.InsufficientFundException;
import com.example.footballmanager.exeption.PlayerNotFoundException;
import com.example.footballmanager.exeption.TeamNotFoundException;
import com.example.footballmanager.exeption.UnsupportedTransferException;
import com.example.footballmanager.repository.PlayerRepository;
import com.example.footballmanager.repository.TeamRepository;
import com.example.footballmanager.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.footballmanager.exeption.constants.ErrorMessageConstants.NOT_ENOUGH_MONEY_ON_ACCOUNT;
import static com.example.footballmanager.exeption.constants.ErrorMessageConstants.PLAYER_IS_ALREADY_IN_THIS_TEAM;
import static com.example.footballmanager.exeption.constants.ErrorMessageConstants.PLAYER_WAS_NOT_FOUND;
import static com.example.footballmanager.exeption.constants.ErrorMessageConstants.TEAM_WAS_NOT_FOUND;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final long PLAYER_TRANSFER_COEFFICIENT = 100000;
    private static final long PERCENTAGE_COEFFICIENT = 100;

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository,
                             TeamRepository teamRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void savePlayer(AddPlayerDto dto) {
        Player player = modelMapper.map(dto, Player.class);
        playerRepository.save(player);
    }

    @Override
    public PlayerDto getPlayer(Long id) {
        return modelMapper.map(findPlayerById(id), PlayerDto.class);
    }

    @Override
    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream().map(player -> modelMapper.map(player, PlayerDto.class)).collect(Collectors.toList());
    }

    @Override
    public AddPlayerDto updatePlayer(Long id, UpdatePlayerDto dto) {
        Player player = findPlayerById(id);
        Optional.ofNullable(dto.getName()).ifPresent(player::setName);
        Optional.ofNullable(dto.getBirthDate()).ifPresent(player::setBirthDate);
        Optional.ofNullable(dto.getCareerStart()).ifPresent(player::setCareerStart);
        playerRepository.save(player);
        return modelMapper.map(player, AddPlayerDto.class);
    }

    @Override
    public void deletePlayer(Long id) {
        Player player = findPlayerById(id);
        playerRepository.deleteById(player.getId());
    }

    @Override
    public void transferPlayer(Long playerId, Long newTeamId) {
        Player player = findPlayerById(playerId);
        Team oldTeam = findTeamById(player.getTeam().getId());
        Team newTeam = findTeamById(newTeamId);

        if (Objects.equals(oldTeam.getId(), newTeam.getId())) {
            throw new UnsupportedTransferException(PLAYER_IS_ALREADY_IN_THIS_TEAM);
        }

        long transferPrice = calculateTransferPrice(player, oldTeam);
        long newTeamAccount = newTeam.getAccount();

        if (newTeamAccount - transferPrice < 0) {
            throw new InsufficientFundException(
                    String.format(NOT_ENOUGH_MONEY_ON_ACCOUNT, transferPrice, newTeamAccount));
        }

        newTeam.setAccount(newTeam.getAccount() - transferPrice);
        oldTeam.setAccount(oldTeam.getAccount() + transferPrice);
        player.setTeam(newTeam);

        playerRepository.save(player);
        teamRepository.save(newTeam);
        teamRepository.save(oldTeam);
    }

    private long calculateTransferPrice(Player player, Team oldTeam) {
        long playerExperience = ChronoUnit.YEARS.between(player.getCareerStart(), LocalDate.now());
        long playerAge = ChronoUnit.YEARS.between(player.getBirthDate(), LocalDate.now());
        long transferPrice = (playerExperience * PLAYER_TRANSFER_COEFFICIENT) / playerAge;
        long commission = (transferPrice * oldTeam.getTransferCommission()) / PERCENTAGE_COEFFICIENT;
        transferPrice += commission;
        return transferPrice;
    }

    private Player findPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(
                        () -> new PlayerNotFoundException(String.format(PLAYER_WAS_NOT_FOUND, id)));
    }

    private Team findTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(
                () -> new TeamNotFoundException(String.format(TEAM_WAS_NOT_FOUND, id)));
    }
}
