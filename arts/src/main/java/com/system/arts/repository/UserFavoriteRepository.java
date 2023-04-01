package com.system.arts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.arts.entity.UserFavorite;

@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Integer> {

    List<UserFavorite> findByUserId(int userId);

    List<UserFavorite> findByResourceId(int resourceId);

    void deleteByUserIdAndResourceId(int userId, int resourceId);
}
