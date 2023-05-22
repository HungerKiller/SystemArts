package com.system.arts.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.system.arts.dto.ResourceDto;
import com.system.arts.entity.Resource;
import com.system.arts.service.ResourceService;

@RestController
@CrossOrigin
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;
    
    @Autowired
	private ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<List<ResourceDto>> getAllResources() {
        List<ResourceDto> resources = resourceService.getAllResources().stream()
                .map(post -> modelMapper.map(post, ResourceDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDto> getResourceById(@PathVariable int id) {
        Resource resource = resourceService.getResourceById(id);
        ResourceDto resourceDto = modelMapper.map(resource, ResourceDto.class);
        return ResponseEntity.ok(resourceDto);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Resource>> getResourcesByUserId(@PathVariable int userId) {
        List<Resource> resources = resourceService.getResourcesByUserId(userId);
        return ResponseEntity.ok(resources);
    }

    @PostMapping("/")
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        Resource createdResource = resourceService.createResource(resource);
        return new ResponseEntity<>(createdResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable int id, @RequestBody Resource resource) {
        resource.setId(id);
        Resource updatedResource = resourceService.updateResource(resource);
        return ResponseEntity.ok(updatedResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable int id) {
        resourceService.deleteResource(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
