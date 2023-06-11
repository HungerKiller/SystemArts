package com.system.arts.service;

import java.util.List;
import java.util.Optional;

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

    public List<ResourceFile> getResourceFilesByResourceId(int resourceId) {
        return resourceFileRepository.findByResourceId(resourceId);
    }

    public ResourceFile createResourceFile(ResourceFile resourceFile) {
        return resourceFileRepository.save(resourceFile);
    }

    public ResourceFile updateResourceFile(ResourceFile updatedResourceFile) {
        Optional<ResourceFile> optionalResourceFile = resourceFileRepository.findById(updatedResourceFile.getId());
        if (optionalResourceFile.isPresent()) {
            ResourceFile existingResourceFile = optionalResourceFile.get();
            existingResourceFile.setIsValid(updatedResourceFile.getIsValid());
            return resourceFileRepository.save(existingResourceFile);
        } else {
            throw new IllegalArgumentException("Resource not found with id " + updatedResourceFile.getId());
        }
    }

    public void deleteResourceFile(int id) {
        ResourceFile ResourceFile = resourceFileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource file not found with id " + id));
        resourceFileRepository.delete(ResourceFile);
    }
}
