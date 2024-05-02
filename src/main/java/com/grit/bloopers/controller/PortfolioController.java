package com.grit.bloopers.controller;


import com.grit.bloopers.dto.PortfolioDTO;
import com.grit.bloopers.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/portfolios")
    public ResponseEntity<PortfolioDTO> createPortfolio(@RequestBody PortfolioDTO portfolioDTO) {
        portfolioService.createPortfolio(portfolioDTO);
        return new ResponseEntity<>(portfolioDTO, HttpStatus.CREATED);

    }
    @GetMapping("/portfolios")
    public List<PortfolioDTO> getPortfolioList() {
        return portfolioService.getPortfolioList();
    }

    @GetMapping("/portfolios/{id}")
    public PortfolioDTO getPortfolio(@PathVariable int id) {
       return portfolioService.getPortfolio(id);
    }

    @PatchMapping("/portfolios/{id}")
    public void updatePortfolio(@PathVariable int id, @RequestBody PortfolioDTO portfolioDTO) {
        portfolioService.updatePortfolio(portfolioDTO);
    }

    @DeleteMapping("/portfolios/{id}")
    public void deletePortfolio(@PathVariable int id) {
        portfolioService.deletePortfolio(id);

    }

}
