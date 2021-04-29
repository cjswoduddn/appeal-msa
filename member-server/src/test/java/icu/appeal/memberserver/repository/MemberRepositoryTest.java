package icu.appeal.memberserver.repository;

import icu.appeal.commonlibrary.domain.member.Member;
import icu.appeal.commonlibrary.dto.member.SignInDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@EntityScan(basePackages = {"icu.appeal.commonlibrary"})
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 저장 잘 되는지!")
    public void saveTest() throws Exception{
        //given
        SignInDto signInDto = new SignInDto();
        signInDto.setEmail("testEmail");
        Member member = Member.createMember(signInDto);
        //when
        memberRepository.save(member);
        //then
        assertNotNull(member.getId());
    }

}