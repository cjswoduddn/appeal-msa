package icu.appeal.commonlibrary.dto.template.templatetwo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter @Setter
public class TemplateTwoProjectDto {

    private String name;
    private String intro;
    private String role;
    private String thumbnail;

    public static TemplateTwoProjectDto from(TemplateTwoProjectFileDto project) {
        TemplateTwoProjectDto templateTwoProjectDto = new TemplateTwoProjectDto();
        BeanUtils.copyProperties(project, templateTwoProjectDto);
        return templateTwoProjectDto;
    }
}
