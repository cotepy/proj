package com.hello.core.beanFind;

import com.hello.core.AppConfig;
import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.FixedDiscountPolicy;
import com.hello.core.discount.VariableDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextInheritanceFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시 자식 타입 2개 이상이면 중복 오류 발생")
    void findBeanByParentTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식 타입 2개 이상이면, 빈 이름을 지정한다")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy variableDiscountPolicy = ac.getBean("variableDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(variableDiscountPolicy).isInstanceOf(VariableDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBySubType() {
        VariableDiscountPolicy bean = ac.getBean(VariableDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(VariableDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 전부 조회")
    void findAllBeansByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
            
        }
    }

    @Test
    @DisplayName("부모 타입으로 전부 조회 - Object 타입")
    void findAllBeansByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy variableDiscountPolicy() {
            return new VariableDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixedDiscountPolicy() {
            return new FixedDiscountPolicy();
        }
    }
}
