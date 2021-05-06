package icu.appeal.commonlibrary.service;

import icu.appeal.commonlibrary.domain.member.Member;
import icu.appeal.commonlibrary.dto.template.common.TemplateRequestDto;

public interface TemplateService<T extends TemplateRequestDto> {
    Long createTemplate(Member member, T dto);
}
