package icu.appeal.commonlibrary.domain.portfolio;

import icu.appeal.commonlibrary.domain.member.Member;
import icu.appeal.commonlibrary.dto.portfolio.PortfolioDto;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Builder
@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PORTFOLIO_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String thumbnail;
    private String title;
    private String skill;
    private String name;
    private String intro;
    private String templateType;

    public static Portfolio of(PortfolioDto portfolioDto, Member member, String type) {
        Portfolio portfolio = new Portfolio();
        BeanUtils.copyProperties(portfolioDto, portfolio);
        portfolio.member = member;
        portfolio.templateType = type;
        return portfolio;
    }
}
