package com.hello.core.discount;

import com.hello.core.annotation.MainDiscountPolicy;
import com.hello.core.member.Member;
import com.hello.core.member.Status;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class VariableDiscountPolicy implements DiscountPolicy {

    private static int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getStatus()== Status.VIP) return price * discountPercent/100;
        return 0;
    }
}
