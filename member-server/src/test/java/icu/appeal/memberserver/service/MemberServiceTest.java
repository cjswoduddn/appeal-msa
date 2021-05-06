package icu.appeal.memberserver.service;

import icu.appeal.commonlibrary.domain.member.Member;
import icu.appeal.commonlibrary.dto.member.SignInDto;
import icu.appeal.memberserver.repository.MemberRepository;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

class MemberServiceTest {
    
    MemberRepository memberRepository;
    MemberService memberService;
    PasswordEncoder passwordEncoder;
    MockedStatic<Member> mMember;

    @Before
    void before(){
        memberRepository = mock(MemberRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        memberService = new MemberService(memberRepository, passwordEncoder);
        mMember = mockStatic(Member.class);
    }
    
    @Test
    @DisplayName("중복회원방지테스트")
    public void duplicatException() throws Exception{
        //given
        SignInDto signInDto = new SignInDto();
        signInDto.setEmail("testEmail");
        Member member = Member.createMember(signInDto);
        when(Member.createMember(signInDto)).thenReturn(member);
    }

}