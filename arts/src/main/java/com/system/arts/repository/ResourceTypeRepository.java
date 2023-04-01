package com.system.arts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.arts.entity.ResourceType;

@Repository
public interface ResourceTypeRepository extends JpaRepository<ResourceType, Integer> {

}
 