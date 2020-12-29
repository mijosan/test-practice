package com.example.redis;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import com.example.redis.domain.Point;
import com.example.redis.domain.PointRedisRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RedisTest1 {
    
    @Autowired
    private PointRedisRepository pointRedisRepository;

    @AfterEach
    public void tearDown() throws Exception {
        pointRedisRepository.deleteAll();
    }

    @Test
    public void 기본_등록_조회기능() {
        // given
        String id = "mijosan";
        Long amount = 1000L;
        LocalDateTime refreshTime = LocalDateTime.of(2018, 5, 26, 0, 0);
        Point point = Point.builder()
            .id(id)
            .amount(amount)
            .refreshTime(refreshTime)
            .build();

        // when
        pointRedisRepository.save(point);

        // then
        Point savedPoint = pointRedisRepository.findById(id).get();
        assertThat(savedPoint.getAmount()).isEqualTo(1000L);
        assertThat(savedPoint.getRefreshTime()).isEqualTo(refreshTime);
    }

    @Test
    public void 수정기능() {
        // given
        String id = "mijosan";
        Long amount = 1000L;
        LocalDateTime refreshTime = LocalDateTime.of(2018, 5, 26, 0, 0);
        pointRedisRepository.save(Point.builder()
            .id(id)
            .amount(amount)
            .refreshTime(refreshTime)
            .build());
        
        // when
        Point savedPoint = pointRedisRepository.findById(id).get();
        savedPoint.refresh(2000L, LocalDateTime.of(2018, 6, 1, 0, 0));
        pointRedisRepository.save(savedPoint);

        // then
        Point refreshPoint = pointRedisRepository.findById(id).get();
        assertThat(refreshPoint.getAmount()).isEqualTo(2000L);
    }

}
