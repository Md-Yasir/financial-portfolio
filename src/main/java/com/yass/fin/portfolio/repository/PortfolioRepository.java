package com.yass.fin.portfolio.repository;

import com.yass.fin.portfolio.model.UserRoleLinkT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<UserRoleLinkT, Integer> {

}
