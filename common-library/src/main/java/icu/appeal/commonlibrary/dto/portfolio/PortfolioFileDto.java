package icu.appeal.commonlibrary.dto.portfolio;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class PortfolioFileDto {
    private MultipartFile thumbnail;
    private String title;
    private String skill;
    private String name;
    private String intro;
}
