package icu.appeal.memberserver.controller;

import icu.appeal.commonlibrary.code.SuccessCode;
import icu.appeal.commonlibrary.dto.member.SignInDto;
import icu.appeal.commonlibrary.dto.response.SuccessResponse;
import icu.appeal.memberserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<SuccessResponse<Long>> registerMember(@RequestBody SignInDto signInDto) {
        return new ResponseEntity<>(
                SuccessResponse.of(SuccessCode.CREATE_MEMBER,
                        memberService.saveMember(signInDto)
                ), HttpStatus.CREATED
        );
    }
}
