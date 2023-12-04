package com.hello.core.member;

public interface MemberService {
    void signUp(Member member);

    Member findMember(Long memberId);
}
