package com.system.arts.controller;

import com.system.arts.entity.Announcement;
import com.system.arts.service.AnnouncementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/")
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("/{id}")
    public Announcement getAnnouncementById(@PathVariable int id) {
        return announcementService.getAnnouncementById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        Announcement createdAnnouncement = announcementService.createAnnouncement(announcement);
        return new ResponseEntity<>(createdAnnouncement, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public Announcement updateAnnouncement(@RequestBody Announcement announcement, @PathVariable int id) {
        announcement.setId(id);
        return announcementService.updateAnnouncement(announcement);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable int id) {
        announcementService.deleteAnnouncement(id);
    }
}
