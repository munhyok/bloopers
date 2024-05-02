package com.grit.bloopers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grit.bloopers.dto.UserDTO;
import com.grit.bloopers.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(controllers = UserController.class)
@ActiveProfiles({"Register Test"})
public class UserControllerTest {
    @MockBean
    UserService registerService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;



    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .defaultRequest(post("/**").with(csrf()))
                .defaultRequest(put("/**").with(csrf()))
                .defaultRequest(delete("/**").with(csrf()))
                .build();
    }


    UserDTO userDTO =
            UserDTO.builder()
                    .email("test@gmail.com")
                    .name("test")
                    .password("test")
                    .phoneNumber("01012345678")
                    .profileImage("string")
                    .build();





    @Test
    @WithMockUser
    @DisplayName("회원가입 테스트")
    void createUserTest() throws Exception{
        String json = new ObjectMapper().writeValueAsString(userDTO);

        mockMvc.perform(post("/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isCreated())
                .andDo(print());


    }
}
