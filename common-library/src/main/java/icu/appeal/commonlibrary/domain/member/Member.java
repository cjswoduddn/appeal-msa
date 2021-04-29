package icu.appeal.commonlibrary.domain.member;

import icu.appeal.commonlibrary.domain.BaseTimeInfo;
import icu.appeal.commonlibrary.domain.common.Authority;
import icu.appeal.commonlibrary.dto.member.SignInDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public static Member createMember(SignInDto signInDto) {
        Member member = new Member();
        member.email = signInDto.getEmail();
        member.name = signInDto.getName();
        member.password = signInDto.getPassword();
        member.authority = Authority.ROLE_PRE;
        return member;
    }
}
