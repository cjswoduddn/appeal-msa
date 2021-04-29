package icu.appeal.commonlibrary.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignInDto {
    private String email;
    private String password;
    private String name;
}
