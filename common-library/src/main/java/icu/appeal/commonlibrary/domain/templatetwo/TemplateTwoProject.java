package icu.appeal.commonlibrary.domain.templatetwo;

import icu.appeal.commonlibrary.dto.template.templatetwo.TemplateTwoProjectDto;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

import static org.springframework.beans.BeanUtils.*;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TemplateTwoProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PORTFOLIO_ID")
    private TemplateTwo templateTwo;

    private String name;
    private String intro;
    private String role;
    private String thumbnail;

    public static TemplateTwoProject of(TemplateTwoProjectDto project, TemplateTwo templateTwo) {
        TemplateTwoProject templateTwoProject = new TemplateTwoProject();
        copyProperties(project, templateTwoProject);
        templateTwoProject.templateTwo = templateTwo;
        return templateTwoProject;
    }
}
