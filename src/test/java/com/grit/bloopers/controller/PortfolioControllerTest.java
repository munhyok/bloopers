package com.grit.bloopers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grit.bloopers.config.SecurityConfig;
import com.grit.bloopers.dto.PortfolioDTO;
import com.grit.bloopers.service.PortfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = PortfolioController.class)
@ActiveProfiles({"Portfolio Test"})
public class PortfolioControllerTest {

    @MockBean
    PortfolioService portfolioService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .defaultRequest(get("/**").with(csrf()))
                .defaultRequest(post("/**").with(csrf()))
                .defaultRequest(put("/**").with(csrf()))
                .defaultRequest(delete("/**").with(csrf()))
                .build();
    }


    PortfolioDTO portfolioDTO =
            PortfolioDTO.builder()

                    .user_id(1)
                    .like_id(1)
                    .portfolio_name("포트폴리오")
                    .description("포트폴리오 설명")
                    .portfolio_url("http://portfoliotest.com")
                    .created_at(LocalDateTime.now())
                    .build();



    @Test
    @WithMockUser
    @DisplayName("전체 리스트 가져오기")
    void getPortfolioListTest() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/portfolios"))
                .andExpect(status().isOk());


        //assertEquals(portfolioDTO.getPortfolio_name(), "포트폴리오");
    }

    @Test
    @WithMockUser
    @DisplayName("GET Portfolio id")
    void getPortfolioByIdTest() throws Exception {
        String portfolioId = "1";
        given(portfolioService.getPortfolio(1)).willReturn(portfolioDTO);

        mockMvc.perform(get("/portfolios/" + portfolioId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.portfolio_name").value(portfolioDTO.getPortfolio_name()))
                .andExpect(jsonPath("$.portfolio_url").value(portfolioDTO.getPortfolio_url()))
                .andExpect(jsonPath("$.description").value(portfolioDTO.getDescription()))
                .andDo(print());

        verify(portfolioService).getPortfolio(1);

    }

    @Test
    @WithMockUser
    @DisplayName("POST Portfolio")
    void createPortfolioTest() throws Exception {
        PortfolioDTO createPortfolioDTO =
                PortfolioDTO.builder()
                .portfolio_name("test")
                        .portfolio_url("testurl")
                        .description("Test description")
                        .created_at(LocalDateTime.now())
                        .user_id(1).like_id(1).build();

        /*
        직렬화 역직렬화 오류
        Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310"
        을 해결하기 위해서 .registerModule(new JavaTimeModule()) 추가
         */
        String json = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(createPortfolioDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/portfolios-upload")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json).with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.portfolio_name").value(createPortfolioDTO.getPortfolio_name()))
                .andExpect(jsonPath("$.portfolio_url").value(createPortfolioDTO.getPortfolio_url()))
                .andExpect(jsonPath("$.description").value(createPortfolioDTO.getDescription()))
                .andDo(print());

    }
}
