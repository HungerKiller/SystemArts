package com.system.arts.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.system.arts.entity.Resource;
import com.system.arts.service.ResourceService;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/")
    public ResponseEntity<List<Resource>> getAllResources() {
        List<Resource> resources = resourceService.getAllResources();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable int id) {
        Resource resource = resourceService.getResourceById(id);
        return ResponseEntity.ok(resource);
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

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadFile(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try {
            Resource resource = resourceService.getResourceById(id);

            // Delete old file
            File oldFile = new File(resource.getAddress());
            if (oldFile.exists()) {
                oldFile.delete();
            }

            // Save new file
            Path path = Paths.get(uploadDir + resource.getId() + file.getOriginalFilename());
            Files.deleteIfExists(path);
            Files.copy(file.getInputStream(), path);

            resource.setAddress(path.toString());
            resourceService.updateResource(resource);
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error uploading file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable int id) throws IOException {
        Resource resource = resourceService.getResourceById(id);
        File file = new File(resource.getAddress());
        if (file.exists()) {
            Path path = Paths.get(resource.getAddress());
            byte[] fileContent = Files.readAllBytes(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",
                    new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentLength(file.length());
            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}
