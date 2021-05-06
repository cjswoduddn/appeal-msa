package icu.appeal.templatetwoserver.service;

import icu.appeal.commonlibrary.domain.member.Member;
import icu.appeal.commonlibrary.domain.templatetwo.TemplateTwo;
import icu.appeal.commonlibrary.dto.template.templatetwo.TemplateTwoDto;
import icu.appeal.commonlibrary.dto.template.templatetwo.TemplateTwoFileDto;
import icu.appeal.commonlibrary.service.TemplateService;
import icu.appeal.commonlibrary.util.AwsS3Service;
import icu.appeal.templatetwoserver.repository.TemplateTwoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TemplateTwoService implements TemplateService<TemplateTwoFileDto> {

    private final TemplateTwoRepository templateTwoRepository;
    private final AwsS3Service awsS3Service;

    @Override
    public Long createTemplate(Member member, TemplateTwoFileDto dto) {
        return
                templateTwoRepository.save(
                        TemplateTwo.from(convertFileDtoToDto(dto), member)
                )
                        .getId();
    }

    private TemplateTwoDto convertFileDtoToDto(TemplateTwoFileDto templateTwoFileDto) {
        TemplateTwoDto templateTwoDto = TemplateTwoDto.from(templateTwoFileDto);
        templateTwoDto
                .getPortfolio()
                .setThumbnail(
                        awsS3Service.upload(templateTwoFileDto
                                .getPortfolio()
                                .getThumbnail())
                );
        // 리스트에 대해선 거의 답이 없는 수준이긴 한데?
        // s3기술을 dto에 파라미터로 전달해 기술 의존적인 코드를 만들 것이냐
        // 이걸 어떻게 해결해?
        return templateTwoDto;
    }
}
