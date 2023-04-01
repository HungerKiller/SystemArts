package com.system.arts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.arts.entity.ResourceType;
import com.system.arts.repository.ResourceTypeRepository;

@Service
public class ResourceTypeService {

    @Autowired
    private ResourceTypeRepository resourceTypeRepository;

    public List<ResourceType> getAllResourceTypes() {
        return resourceTypeRepository.findAll();
    }

    public ResourceType getResourceTypeById(int id) {
        return resourceTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource type not found with id " + id));
    }

    public ResourceType createResourceType(ResourceType resourceType) {
        return resourceTypeRepository.save(resourceType);
    }

    public ResourceType updateResourceType(ResourceType updatedResourceType) {
        Optional<ResourceType> optionalResourceType = resourceTypeRepository.findById(updatedResourceType.getId());
        if (optionalResourceType.isPresent()) {
            ResourceType existingResourceType = optionalResourceType.get();
            existingResourceType.setName(updatedResourceType.getName());
            existingResourceType.setDescription(updatedResourceType.getDescription());
            return resourceTypeRepository.save(existingResourceType);
        } else {
            throw new IllegalArgumentException("Resource type not found with id " + updatedResourceType.getId());
        }
    }

    public void deleteResourceType(int id) {
        ResourceType resourceType = resourceTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource type not found with id " + id));
        resourceTypeRepository.delete(resourceType);
    }
}
