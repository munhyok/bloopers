package com.grit.bloopers.service;

import com.grit.bloopers.dto.PortfolioDTO;
import com.grit.bloopers.mapper.PortfolioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/*
    @Autowired
    스프링 프레임워크에서 사용되는 애노테이션 중 하나로, 의존성 자동 주입(Dependency Injection)을 수행하는 데 사용
    @Autowired 어노테이션은 스프링 컨테이너가 해당 타입의 빈을 찾아서 자동으로 주입하는 역할
 */

@Service
public class PortfolioService {
    @Autowired
    private PortfolioMapper portfolioMapper;

    public PortfolioService(){
    }

    public PortfolioService(PortfolioMapper portfolioMapper){
        this.portfolioMapper = portfolioMapper;
    }

    @Transactional
    public void createPortfolio(PortfolioDTO portfolioDTO) {
        portfolioMapper.createPortfolio(portfolioDTO);
    }

    public List<PortfolioDTO> getPortfolioList() {
        return portfolioMapper.getPortfolioList();
    }

    public void updatePortfolio(PortfolioDTO portfolioDTO) {
        portfolioMapper.updatePortfolio(portfolioDTO);
    }

    public PortfolioDTO getPortfolio(int id) {
        return portfolioMapper.getPortfolio(id);

    }

    public void deletePortfolio(int id) {
        portfolioMapper.deletePortfolio(id);
    }


}
