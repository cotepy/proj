package com.hello.core.discount;

import com.hello.core.member.Member;
import com.hello.core.member.Status;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableDiscountPolicyTest {

    VariableDiscountPolicy discountPolicy = new VariableDiscountPolicy();

    @Test
    @DisplayName("VIPs get a 10% discount")
    void isVIP() {
        // given
        Member memberJames = new Member(3L, "James", Status.VIP);

        // when
        int num = discountPolicy.discount(memberJames, 20_000);

        // then
        assertThat(num).isEqualTo(2_000);
    }

    @Test
    @DisplayName("Basic status customers do not get a discount")
    void notVIP() {
        // given
        Member memberHarry = new Member(4L, "Harry", Status.BASIC);

        // when
        int num2 = discountPolicy.discount(memberHarry, 10_000);

        // then
        assertThat(num2).isEqualTo(0);
    }

}
