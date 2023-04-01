package com.system.arts.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "is_display")
    private boolean isDisplay;

    public Announcement() {}

    public Announcement(String title, String content, boolean isDisplay) {
        this.title = title;
        this.content = content;
        this.isDisplay = isDisplay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(boolean isDisplay) {
        this.isDisplay = isDisplay;
    }
}
