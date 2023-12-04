package com.hello.core;

import com.hello.core.member.Member;
import com.hello.core.member.MemberService;
import com.hello.core.member.Status;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = appContext.getBean("memberService", MemberService.class);

        Member memberOne = new Member(1L, "Daniel", Status.VIP);
        memberService.signUp(memberOne);

        System.out.println("your search result: " + memberService.findMember(1L).getName());

    }
}
