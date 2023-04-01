package com.system.arts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.arts.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByResourceId(int resourceId);
}
