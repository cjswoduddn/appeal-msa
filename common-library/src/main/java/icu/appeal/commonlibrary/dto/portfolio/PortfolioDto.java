package icu.appeal.commonlibrary.dto.portfolio;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter @Setter
public class PortfolioDto {
    private String thumbnail;
    private String title;
    private String skill;
    private String name;
    private String intro;

    public static PortfolioDto from(PortfolioFileDto portfolio) {
        PortfolioDto portfolioDto = new PortfolioDto();
        BeanUtils.copyProperties(portfolio, portfolioDto);
        return portfolioDto;
    }
}
