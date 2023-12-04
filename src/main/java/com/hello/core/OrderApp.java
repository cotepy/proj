package com.hello.core;

import com.hello.core.member.Member;
import com.hello.core.member.MemberService;
import com.hello.core.member.Status;
import com.hello.core.order.Order;
import com.hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = appContext.getBean("memberService", MemberService.class);
        OrderService orderService = appContext.getBean("orderService", OrderService.class);

        Long memberId = 2L;
        Member memberTwo = new Member(memberId, "Philip", Status.VIP);
        memberService.signUp(memberTwo);

        Order orderTwo = orderService.createOrder(memberId, "chip", 10_000);
        System.out.println("orderTwo = " + orderTwo.toString());
    }
}
