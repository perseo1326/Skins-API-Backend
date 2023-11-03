package com.perseo1326.testBackend.repositories;

import com.perseo1326.testBackend.models.SkinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SkinUserRepository extends JpaRepository<SkinUser, Long> {

    @Query("SELECT u FROM SkinUser u WHERE userId = :userId")
    List<SkinUser> findSkinsByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("DELETE FROM SkinUser u WHERE u.id = :skinUserId ")
    void deleteBySkinId(@Param("skinUserId") Long skinUserId);

    @Query("SELECT s FROM SkinUser s WHERE s.userId = :userId AND s.skinId = :skinId ")
    Optional<SkinUser> findByUserIdAndSkinId(@Param("userId") Long userId, @Param("skinId") String skinId);
}


