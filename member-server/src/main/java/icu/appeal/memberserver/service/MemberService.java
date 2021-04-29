package icu.appeal.memberserver.service;

import icu.appeal.commonlibrary.code.ErrorCode;
import icu.appeal.commonlibrary.domain.member.Member;
import icu.appeal.commonlibrary.dto.member.SignInDto;
import icu.appeal.commonlibrary.exception.DuplicateEmailException;
import icu.appeal.memberserver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Long saveMember(SignInDto signInDto) {
        checkDuplicateMemberByEmail(signInDto.getEmail());
        signInDto.setPassword(passwordEncoder.encode(signInDto.getPassword())); // 찝찝..
        Member member = Member.createMember(signInDto);
        repository.save(member);
        return member.getId();
    }

    private void checkDuplicateMemberByEmail(String email) {
        repository.findByEmail(email)
                .ifPresent(member -> {
                    throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
                });
    }
}
