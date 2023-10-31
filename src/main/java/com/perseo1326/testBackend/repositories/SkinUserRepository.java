package com.perseo1326.testBackend.repositories;

import com.perseo1326.testBackend.models.SkinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkinUserRepository extends JpaRepository<SkinUser, Long> {

    @Query("SELECT u FROM SkinUser u where userId = :userId")
    List<SkinUser> findSkinsByUserId(@Param("userId") Long userId);
}


