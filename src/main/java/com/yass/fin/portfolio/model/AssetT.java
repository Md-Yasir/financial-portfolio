package com.yass.fin.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asset_t")
public class AssetT {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_seq")
    @SequenceGenerator(name = "asset_seq", sequenceName = "asset_t_seq", allocationSize = 1)
    @Column(name = "asset_id")
    private Integer assetID;

    private String ticker;
    private double quantity;
    private double purchasePrice;

    @ManyToOne
    @JoinColumn(name="portfolio_id", referencedColumnName="portfolio_id", insertable = false, updatable = false)
    private PortfolioT portfolioT;

    private LocalDateTime addedAt;
}
