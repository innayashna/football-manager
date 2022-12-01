package com.example.footballmanager.exeption.constants;

public final class ErrorMessageConstants {

    private ErrorMessageConstants() {
    }

    public static final String TEAM_WAS_NOT_FOUND = "Team was not found by this id: %d.";
    public static final String PLAYER_WAS_NOT_FOUND = "Player was not found by this id: %d.";
    public static final String NOT_ENOUGH_MONEY_ON_ACCOUNT
            = "There is not enough money on team account. Required: %d, got: %d.";
    public static final String PLAYER_IS_ALREADY_IN_THIS_TEAM = "Player is already in this team.";
}
