package com.system.arts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.arts.entity.ResourceFile;

@Repository
public interface ResourceFileRepository extends JpaRepository<ResourceFile, Integer> {
    List<ResourceFile> findByResourceId(int resourceId);
}