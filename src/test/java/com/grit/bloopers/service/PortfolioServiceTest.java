package com.grit.bloopers.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.anyInt;
import static org.junit.jupiter.api.Assertions.*;

import com.grit.bloopers.dto.PortfolioDTO;
import com.grit.bloopers.mapper.PortfolioMapper;

import com.grit.bloopers.utils.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.List;






@ExtendWith(MockitoExtension.class)
public class PortfolioServiceTest {
    @InjectMocks
    PortfolioService portfolioService;

    @Mock
    SessionUtil sessionUtil;

    @Mock
    PortfolioMapper portfolioMapper;




    PortfolioDTO portfolioDTO =
            PortfolioDTO.builder()
                .id(1)
                .user_id(1)
                .like_id(1)
                .portfolio_name("포트폴리오")
                .description("test")
                .portfolio_url("https://portfolio.com")
                .created_at(LocalDateTime.now())
                .build();









    @Test
    @DisplayName("포트폴리오 업로드")
    public void createPortfolioTest() {


        doNothing().when(portfolioMapper).createPortfolio(any(PortfolioDTO.class));
        portfolioService.createPortfolio(portfolioDTO);

        verify(portfolioMapper).createPortfolio(any(PortfolioDTO.class));

    }

    @Test
    @DisplayName("포트폴리오 리스트 불러오기")
    public void getPortfolioListTest() {
        when(portfolioMapper.getPortfolioList()).thenReturn(List.of(portfolioDTO));
        portfolioService.getPortfolioList();
        verify(portfolioMapper).getPortfolioList();
    }

    @Test
    @DisplayName("특정 id 포트폴리오 불러오기")
    public void getPortfolioByIdTest() {
        when(portfolioMapper.getPortfolio(anyInt())).thenReturn(portfolioDTO);
        //portfolioService.getPortfolio(1);
        assertEquals(portfolioDTO, portfolioService.getPortfolio(111));
        verify(portfolioMapper).getPortfolio(anyInt());
    }
}
