package com.grit.bloopers.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString

public class PortfolioMsgDTO {
    private PortfolioDTO portfolioDTO;
    private List<Integer> followers;


}
