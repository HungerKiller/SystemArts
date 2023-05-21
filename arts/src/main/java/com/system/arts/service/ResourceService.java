package com.system.arts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.arts.entity.Resource;
import com.system.arts.repository.ResourceRepository;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Resource getResourceById(int id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource not found with id " + id));
    }

    public List<Resource> getResourcesByUserId(int userId) {
        return resourceRepository.findByUserId(userId);
    }

    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public Resource updateResource(Resource updatedResource) {
        Optional<Resource> optionalResource = resourceRepository.findById(updatedResource.getId());
        if (optionalResource.isPresent()) {
            Resource existingResource = optionalResource.get();
            existingResource.setTitle(updatedResource.getTitle());
            existingResource.setDescription(updatedResource.getDescription());
            existingResource.setClickCount(updatedResource.getClickCount());
            existingResource.setPrice(updatedResource.getPrice());
            existingResource.setUser(updatedResource.getUser());
            existingResource.setResourceType(updatedResource.getResourceType());
            return resourceRepository.save(existingResource);
        } else {
            throw new IllegalArgumentException("Resource not found with id " + updatedResource.getId());
        }
    }

    public void deleteResource(int id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource not found with id " + id));
        resourceRepository.delete(resource);
    }
}
