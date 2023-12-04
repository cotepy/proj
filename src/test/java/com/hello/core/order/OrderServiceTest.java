package com.hello.core.order;

import com.hello.core.AppConfig;
import com.hello.core.member.Member;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;
import com.hello.core.member.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // given
        Long memberId = 2L;
        Member memberTwo = new Member(memberId, "Philip", Status.VIP);
        memberService.signUp(memberTwo);

        // when
        Order orderNum = orderService.createOrder(memberId, "chip", 10_000);

        // then
        Assertions.assertThat(orderNum.getDiscountPrice()).isEqualTo(1_000);
    }

}
