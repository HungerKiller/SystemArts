package com.system.arts.controller;

import com.system.arts.entity.ResourceType;
import com.system.arts.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/resource-types")
public class ResourceTypeController {

    @Autowired
    private ResourceTypeService resourceTypeService;

    @GetMapping("/")
    public ResponseEntity<List<ResourceType>> getAllResourceTypes() {
        List<ResourceType> resourceTypes = resourceTypeService.getAllResourceTypes();
        return ResponseEntity.ok(resourceTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceType> getResourceTypeById(@PathVariable int id) {
        ResourceType resourceType = resourceTypeService.getResourceTypeById(id);
        return ResponseEntity.ok(resourceType);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<ResourceType> createResourceType(@RequestBody ResourceType resourceType) {
        ResourceType createdResourceType = resourceTypeService.createResourceType(resourceType);
        return new ResponseEntity<>(createdResourceType, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResourceType> updateResourceType(@PathVariable int id, @RequestBody ResourceType resourceType) {
        resourceType.setId(id);
        ResourceType updatedResourceType = resourceTypeService.updateResourceType(resourceType);
        return ResponseEntity.ok(updatedResourceType);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResourceType(@PathVariable int id) {
        resourceTypeService.deleteResourceType(id);
        return ResponseEntity.noContent().build();
    }
}
