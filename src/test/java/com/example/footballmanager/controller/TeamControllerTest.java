package com.example.footballmanager.controller;

import com.example.footballmanager.service.impl.PlayerServiceImpl;
import com.example.footballmanager.service.impl.TeamServiceImpl;
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
class TeamControllerTest {

    private static final String TEAM_CONTROLLER_LINK = "http://localhost:8080/api/v1/team/";

    private MockMvc mockMvc;

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamServiceImpl teamService;

    @Mock
    private Validator mockValidator;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(teamController)
                .setValidator(mockValidator)
                .build();
    }

    @Test
    void addTeam() throws Exception {
        String content = "{\n"
                + "  \"name\": \"name\",\n"
                + "  \"account\": 100000,\n"
                + "  \"transferCommission\": 1\n"
                + "}";

        mockMvc.perform(post(TEAM_CONTROLLER_LINK)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getTeam() throws Exception {
        mockMvc.perform(get(TEAM_CONTROLLER_LINK + "1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllTeams() throws Exception {
        mockMvc.perform(get(TEAM_CONTROLLER_LINK))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPlayers() throws Exception {
        mockMvc.perform(get(TEAM_CONTROLLER_LINK + "1/players"))
                .andExpect(status().isOk());
    }

    @Test
    void updateTeam() throws Exception {
        String content = "{\n"
                + "  \"name\": \"name\"\n"
                + "}";

        mockMvc.perform(patch(TEAM_CONTROLLER_LINK + "1")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTeam() throws Exception {
        mockMvc.perform(delete(TEAM_CONTROLLER_LINK + "1"))
                .andExpect(status().isOk());
    }
}