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

    @Value("${file.upload-dir}")
    private String uploadDir;

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
            Path path = Paths.get(uploadDir + resource.getId() + file.getOriginalFilename());
            Files.deleteIfExists(path);
            Files.copy(file.getInputStream(), path);

            // Create resource file
            ResourceFile resourceFile = new ResourceFile(path.toString(), resource);
            resourceFileService.createResourceFile(resourceFile);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
