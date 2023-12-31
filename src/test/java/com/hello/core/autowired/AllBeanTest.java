package com.hello.core.autowired;

import com.hello.core.AutoAppConfig;
import com.hello.core.discount.DiscountPolicy;
import com.hello.core.member.Member;
import com.hello.core.member.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Status.VIP);
        int discountPrice = discountService.discount(member, 10_000, "fixedDiscountPolicy");

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1_000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap: " + policyMap);
            System.out.println("policies: " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
