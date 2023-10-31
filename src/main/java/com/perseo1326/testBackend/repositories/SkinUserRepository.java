package com.perseo1326.testBackend.repositories;

import com.perseo1326.testBackend.models.SkinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkinUserRepository extends JpaRepository<SkinUser, Long> {

    @Query("SELECT u FROM SkinUser u WHERE userId = :userId")
    List<SkinUser> findSkinsByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("DELETE FROM SkinUser u WHERE u.skinId = :skinId AND u.userId = :userId ")
    void deleteBySkinId(@Param("userId") Long userId, @Param("skinId") String skinId);
}


