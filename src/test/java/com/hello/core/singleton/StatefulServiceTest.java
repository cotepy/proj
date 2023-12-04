package com.hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: 사용자 A가 10_000원 주문
        statefulService1.order("userA", 10_000);

        // ThreadB: 사용자B가 20_000원 주문
        statefulService2.order("userB", 20_000);

        // ThreadA: 사용자A 주문 금액 조회
        int price1 = statefulService1.getPrice();
        System.out.println("price1 = " + price1);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20_000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}