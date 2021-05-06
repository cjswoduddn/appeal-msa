package icu.appeal.templatetwoserver.controller;

import icu.appeal.commonlibrary.controller.TemplateController;
import icu.appeal.commonlibrary.dto.template.templatetwo.TemplateTwoFileDto;
import icu.appeal.commonlibrary.service.TemplateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/templatetwo")
@RestController
public class TemplateTwoController extends TemplateController<TemplateTwoFileDto> {
    public TemplateTwoController(TemplateService templateService) {
        super(templateService);
    }
}
