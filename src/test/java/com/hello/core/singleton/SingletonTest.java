package com.hello.core.singleton;

import com.hello.core.AppConfig;
import com.hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureDIContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때 마다 객체를 생성
        // 첫번째 객체
        MemberService memberService1 = appConfig.memberService();

        // 두번째 객체
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른것을 확인
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회: 호출할 때 마다 객체를 생성
        // 첫번째 객체
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 두번째 객체
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 다른것을 확인
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
