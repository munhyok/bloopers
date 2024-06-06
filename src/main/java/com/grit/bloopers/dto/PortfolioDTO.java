package com.grit.bloopers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Builder
public class PortfolioDTO {

    private int id;
    private int user_id;
    private int like_id;
    private String portfolio_name;
    private String description;
    private String portfolio_url;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime created_at;



}