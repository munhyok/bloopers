package com.grit.bloopers.mapper;

import com.grit.bloopers.dto.PortfolioDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper
public interface PortfolioMapper {
    void createPortfolio(PortfolioDTO portfolioDTO);

    void updatePortfolio(PortfolioDTO portfolioDTO);

    PortfolioDTO getPortfolio(int id);

    void deletePortfolio(int id);

    List<PortfolioDTO> getPortfolioList();



}
