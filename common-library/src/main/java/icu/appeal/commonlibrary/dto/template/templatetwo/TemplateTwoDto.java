package icu.appeal.commonlibrary.dto.template.templatetwo;

import icu.appeal.commonlibrary.dto.portfolio.PortfolioDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class TemplateTwoDto {

    private PortfolioDto portfolio;
    private List<TemplateTwoProjectDto> projects = new ArrayList<>();
    private List<TemplateTwoCareerDto> careers = new ArrayList<>();

    public static TemplateTwoDto from(TemplateTwoFileDto templateTwoFileDto){
        TemplateTwoDto templateTwoDto = new TemplateTwoDto();
        templateTwoDto.portfolio = PortfolioDto.from(templateTwoFileDto.getPortfolio());
        templateTwoDto.projects = templateTwoFileDto
                .getProjects()
                .stream()
                .map(project->TemplateTwoProjectDto.from(project))
                .collect(Collectors.toList())
                ;
        templateTwoDto.careers = templateTwoFileDto.getCareers();
        return templateTwoDto;
    }

}
