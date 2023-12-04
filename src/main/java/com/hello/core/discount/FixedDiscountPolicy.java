package com.hello.core.discount;

import com.hello.core.member.Member;
import com.hello.core.member.Status;

public class FixedDiscountPolicy implements DiscountPolicy {

    private int fixedDiscountAmount = 1_000; // 1_000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getStatus() == Status.VIP) {
            return fixedDiscountAmount;
        }
        return 0;
    }
}
