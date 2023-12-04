package com.hello.core.member;

import com.hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void signUp() {
        // given
        Member memberOne = new Member(1L, "Daniel", Status.VIP);

        // when
        memberService.signUp(memberOne);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(memberOne).isEqualTo(findMember);

    }
}
