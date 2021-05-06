package icu.appeal.commonlibrary.domain.templatetwo;

import icu.appeal.commonlibrary.dto.template.templatetwo.TemplateTwoCareerDto;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

import static org.springframework.beans.BeanUtils.*;

@Builder
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TemplateTwoCareer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEMPLATE_ID")
    private TemplateTwo templateTwo;

    private String title;
    private String date;
    private String position;
    private String stack;
    private String intro;

    public static TemplateTwoCareer of(TemplateTwoCareerDto career, TemplateTwo templateTwo) {
        TemplateTwoCareer templateTwoCareer = new TemplateTwoCareer();
        copyProperties(career, templateTwoCareer);
        templateTwoCareer.templateTwo = templateTwo;
        return templateTwoCareer;
    }
}
