package com.system.arts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.arts.entity.ResourceFile;
import com.system.arts.repository.ResourceFileRepository;

@Service
public class ResourceFileService {

    @Autowired
    private ResourceFileRepository resourceFileRepository;

    public List<ResourceFile> getAllResourceFiles() {
        return resourceFileRepository.findAll();
    }

    public ResourceFile getResourceFileById(int id) {
        return resourceFileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource file not found with id " + id));
    }

    public ResourceFile createResourceFile(ResourceFile resourceFile) {
        return resourceFileRepository.save(resourceFile);
    }

    public void deleteResourceFile(int id) {
        ResourceFile ResourceFile = resourceFileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource file not found with id " + id));
        resourceFileRepository.delete(ResourceFile);
    }
}
