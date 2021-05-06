package icu.appeal.commonlibrary.dto.template.templatetwo;

import icu.appeal.commonlibrary.dto.portfolio.PortfolioFileDto;
import icu.appeal.commonlibrary.dto.template.common.TemplateRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TemplateTwoFileDto implements TemplateRequestDto {

    private PortfolioFileDto portfolio;
    private List<TemplateTwoProjectFileDto> projects = new ArrayList<>();
    private List<TemplateTwoCareerDto> careers = new ArrayList<>();

}
