package com.example.footballmanager.controller;

import com.example.footballmanager.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    private static final String PLAYER_CONTROLLER_LINK = "http://localhost:8080/api/v1/player/";

    private MockMvc mockMvc;

    @InjectMocks
    private PlayerController playerController;

    @Mock
    private PlayerServiceImpl playerService;

    @Mock
    private Validator mockValidator;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(playerController)
                .setValidator(mockValidator)
                .build();
    }

    @Test
    void addPlayer() throws Exception {
        String content = "{\n"
                + "  \"name\": \"name\",\n"
                + "  \"age\": 21,\n"
                + "  \"experience\": 1,\n"
                + "  \"teamId\": 1\n"
                + "}";

        mockMvc.perform(post(PLAYER_CONTROLLER_LINK + "/add")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated());
    }

    @Test
    void getPlayer() throws Exception {
        mockMvc.perform(get(PLAYER_CONTROLLER_LINK + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPlayers() throws Exception {
        mockMvc.perform(get(PLAYER_CONTROLLER_LINK + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    void updatePlayer() throws Exception {
        String content = "{\n"
                + "  \"name\": \"Inna\"\n"
                + "}";

        mockMvc.perform(patch(PLAYER_CONTROLLER_LINK + "/update/1")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deletePlayer() throws Exception {
        mockMvc.perform(delete(PLAYER_CONTROLLER_LINK + "/delete/1"));
    }

    @Test
    void transferPlayer() throws Exception {
        mockMvc.perform(patch(PLAYER_CONTROLLER_LINK + "/1/transfer?newTeamId=1"))
                .andExpect(status().isOk());
    }
}