package com.yass.fin.portfolio.repository;

import com.yass.fin.portfolio.model.UserT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserT, Integer> {

  Optional<UserT> findById(Integer userId);

}
