package com.system.arts.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.system.arts.entity.Resource;
import com.system.arts.entity.ResourceFile;
import com.system.arts.service.ResourceFileService;
import com.system.arts.service.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/resource-files")
public class ResourceFileController {

    @Autowired
    private ResourceService resourceService;
    
    @Autowired
    private ResourceFileService resourceFileService;

    @GetMapping("/")
    public List<ResourceFile> getAllResourceFiles() {
        return resourceFileService.getAllResourceFiles();
    }

    @GetMapping("/{id}")
    public ResourceFile getResourceFileById(@PathVariable int id) {
        return resourceFileService.getResourceFileById(id);
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadResourceFile(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try {
            Resource resource = resourceService.getResourceById(id);

            // Save new file
            String uploadDir = "./arts/src/main/resources/static/";
            String name = resource.getId() + "__" +file.getOriginalFilename();
            Path path = Paths.get(uploadDir + name);
            Files.deleteIfExists(path);
            Files.copy(file.getInputStream(), path);

            // Create resource file
            List<ResourceFile> resourceFiles = resourceFileService.getResourceFilesByResourceId(id);
            boolean exists = false;
            for (ResourceFile resourceFile : resourceFiles) {
                if (resourceFile.getPath().equals(path.toString())) {
                    exists = true;
                }
            }

            if (!exists) {
                ResourceFile resourceFile = new ResourceFile(path.toString(), name, false, id);
                resourceFileService.createResourceFile(resourceFile);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceFile> updateResourceFile(@PathVariable int id, @RequestBody ResourceFile resourceFile) {
        resourceFile.setId(id);
        ResourceFile updatedResource = resourceFileService.updateResourceFile(resourceFile);
        return ResponseEntity.ok(updatedResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResourceFile(@PathVariable int id) throws IOException {
        ResourceFile resourceFile = resourceFileService.getResourceFileById(id);
        Path path = Paths.get(resourceFile.getPath());
        Files.deleteIfExists(path);
        resourceFileService.deleteResourceFile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadResourceFile(@PathVariable int id) throws IOException {
        ResourceFile resourceFile = resourceFileService.getResourceFileById(id);
        File file = new File(resourceFile.getPath());
        if (file.exists()) {
            Path path = Paths.get(resourceFile.getPath());
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
