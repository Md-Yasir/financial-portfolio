package com.yass.fin.portfolio.repository;

import com.yass.fin.portfolio.model.UserRoleLinkT;
import com.yass.fin.portfolio.model.UserT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleLinkRepository extends JpaRepository<UserRoleLinkT, Integer> {

}
