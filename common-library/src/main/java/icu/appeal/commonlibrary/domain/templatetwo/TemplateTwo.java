package icu.appeal.commonlibrary.domain.templatetwo;

import icu.appeal.commonlibrary.domain.member.Member;
import icu.appeal.commonlibrary.domain.portfolio.Portfolio;
import icu.appeal.commonlibrary.dto.template.templatetwo.TemplateTwoDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TemplateTwo {

    @Transient
    private static final String type = "templatetwo";

    @Id
    @Column(name = "TEMPLATE_ID")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TEMPLATE_ID")
    private Portfolio portfolio;

    @OneToMany(mappedBy = "templateTwo", cascade = CascadeType.ALL)
    private List<TemplateTwoProject> projects = new ArrayList<>();

    @OneToMany(mappedBy = "templateTwo", cascade = CascadeType.ALL)
    private List<TemplateTwoCareer> careers = new ArrayList<>();



    public static TemplateTwo from(TemplateTwoDto templateTwoDto, Member member) {
        TemplateTwo templateTwo = new TemplateTwo();
        templateTwo.portfolio = Portfolio.of(templateTwoDto.getPortfolio(), member, type);
        templateTwo.projects = templateTwoDto
                .getProjects()
                .stream()
                .map(project->TemplateTwoProject.of(project, templateTwo))
                .collect(Collectors.toList())
                ;
        templateTwo.careers = templateTwoDto
                .getCareers()
                .stream()
                .map(career->TemplateTwoCareer.of(career, templateTwo))
                .collect(Collectors.toList())
                ;
        return templateTwo;
    }
}
