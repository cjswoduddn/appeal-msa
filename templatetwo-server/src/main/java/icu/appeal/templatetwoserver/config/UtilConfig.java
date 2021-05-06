package icu.appeal.templatetwoserver.config;

import icu.appeal.commonlibrary.util.AwsS3Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Bean
    public AwsS3Service awsS3Service(){
        return new AwsS3Service();
    }
}
