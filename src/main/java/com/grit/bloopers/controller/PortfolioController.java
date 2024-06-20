package com.grit.bloopers.controller;


import com.grit.bloopers.dto.PortfolioDTO;
import com.grit.bloopers.dto.PortfolioMsgDTO;
import com.grit.bloopers.service.FollowService;
import com.grit.bloopers.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final FollowService followService;

    private final KafkaTemplate<String, PortfolioMsgDTO> kafkaTemplate;
    private static final String TOPIC = "portfolio-topic";



    @PostMapping("/portfolios-upload")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PortfolioDTO> createPortfolio(@RequestBody PortfolioDTO portfolioDTO) {

        List<Integer> followers = followService.getFollowList();

        PortfolioMsgDTO message = new PortfolioMsgDTO();
        message.setPortfolioDTO(portfolioDTO);
        message.setFollowers(followers);
        kafkaTemplate.send(TOPIC, message);

        portfolioService.createPortfolio(message);
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
