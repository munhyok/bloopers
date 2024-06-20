package com.grit.bloopers.service;

import com.grit.bloopers.dto.PortfolioDTO;
import com.grit.bloopers.dto.PortfolioMsgDTO;
import com.grit.bloopers.exception.error.LoginRequiredException;
import com.grit.bloopers.mapper.PortfolioMapper;
import com.grit.bloopers.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


/*
    @Autowired
    스프링 프레임워크에서 사용되는 애노테이션 중 하나로, 의존성 자동 주입(Dependency Injection)을 수행하는 데 사용
    @Autowired 어노테이션은 스프링 컨테이너가 해당 타입의 빈을 찾아서 자동으로 주입하는 역할


    Spring Boot Security가 아닌 HttpServlet으로 로그인 기능 구현


    kafka listener 구분하기
    피드 관련 코드 추가하기
    비동기 처리가 왜 필요한지 장단점
    성능테스트
    CI/CD
 */

@Service
@RequiredArgsConstructor
public class PortfolioService {


    private final PortfolioMapper portfolioMapper;


    private final RedisTemplate<String, Object> redisTemplate;

    @KafkaListener(topics = "portfolio-topic", groupId = "portfolio-group")
    public void createPortfolio(PortfolioMsgDTO message) {

        //로그인 유무 확인
        //int sessionId = SessionUtil.getLoginUserId();

        /*
        //로그인 하지 않았으면 Exception
        if (sessionId == -1) {
            throw new LoginRequiredException();
        }

        */

        String portfolioData = message.getPortfolioDTO().toString();

        for (int follower : message.getFollowers()) {
            String key = "feed:"+ Integer.toString(follower);
            redisTemplate.opsForSet().add(key, portfolioData);
        }


        //어떤 유저가 업로드했는지 id 정보를 가져오기
        //message.setUser_id(sessionId);

        //portfolio id와 like id를 일치
        //portfolioDTO.setLike_id(portfolioDTO.getId());


        //portfolioMapper.createPortfolio(portfolioDTO);

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
