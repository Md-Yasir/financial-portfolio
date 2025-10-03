package com.yass.fin.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "portfolio_t")
public class PortfolioT {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "portfolio_seq")
    @SequenceGenerator(name = "portfolio_seq", sequenceName = "portfolio_t_seq", allocationSize = 1)
    @Column(name = "portfolio_id")
    private Integer portfolioID;

    private String name;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy="portfolioT")
    private List<AssetT> assetTS;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="user_id", insertable = false, updatable = false)
    private UserT userT;

}
