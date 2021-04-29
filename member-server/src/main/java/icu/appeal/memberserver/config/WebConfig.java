package icu.appeal.memberserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = {icu.appeal.commonlibrary.advice.GlobalExceptionHandler.class})
@Configuration
public class WebConfig {
}
