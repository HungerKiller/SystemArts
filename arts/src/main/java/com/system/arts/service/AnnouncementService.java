package com.system.arts.service;

import com.system.arts.entity.Announcement;
import com.system.arts.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public Announcement getAnnouncementById(int id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Announcement not found with id " + id));
    }

    public Announcement createAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    public Announcement updateAnnouncement(Announcement updatedAnnouncement) {
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(updatedAnnouncement.getId());
        if (optionalAnnouncement.isPresent()) {
            Announcement existingAnnouncement = optionalAnnouncement.get();
            existingAnnouncement.setTitle(updatedAnnouncement.getTitle());
            existingAnnouncement.setContent(updatedAnnouncement.getContent());
            existingAnnouncement.setIsDisplay(updatedAnnouncement.getIsDisplay());
            return announcementRepository.save(existingAnnouncement);
        } else {
            throw new IllegalArgumentException("Announcement not found with id " + updatedAnnouncement.getId());
        }
    }

    public void deleteAnnouncement(int id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Announcement not found with id " + id));
        announcementRepository.delete(announcement);
    }
}

