package icu.appeal.commonlibrary.controller;

import icu.appeal.commonlibrary.domain.member.Member;
import icu.appeal.commonlibrary.dto.template.common.TemplateRequestDto;
import icu.appeal.commonlibrary.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
public class TemplateController <D extends TemplateRequestDto>{

    private final TemplateService templateService;
    @PostMapping
    public ResponseEntity<Long> createTemplate(Member member, D dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(templateService.createTemplate(member, dto))
                ;
    }

}
