package com.hello.core.singleton;

public class SingletonService {

    // 인스턴스 객체 생성
    private static final SingletonService instance = new SingletonService();

    // 객체 조회하는 메서드
    public static SingletonService getInstance() {
        return instance;
    }

    // private 생성자
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
