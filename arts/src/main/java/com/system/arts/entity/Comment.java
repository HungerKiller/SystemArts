package com.system.arts.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_user_id"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_resource_id"))
    private Resource resource;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(new Date().getTime());
        updatedAt = createdAt;
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(new Date().getTime());
    }

    public Comment() {}

    public Comment(String content, User user, Resource resource) {
        this.content = content;
        this.user = user;
        this.resource = resource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
