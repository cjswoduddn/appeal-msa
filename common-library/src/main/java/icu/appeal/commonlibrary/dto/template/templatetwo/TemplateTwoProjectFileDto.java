package icu.appeal.commonlibrary.dto.template.templatetwo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class TemplateTwoProjectFileDto {
    private String name;
    private String intro;
    private String role;
    private MultipartFile thumbnail;

}
