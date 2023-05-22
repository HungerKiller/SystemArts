package com.system.arts.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "clickCount")
    private int clickCount;

    @Column(name = "price")
    private double price;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_user_id"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "resource_type_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_resource_type_id"))
    private ResourceType resourceType;

    @OneToMany(mappedBy = "resourceId", cascade = {CascadeType.REMOVE})
	private List<Comment> comments;

    @OneToMany(mappedBy = "resource", cascade = {CascadeType.REMOVE})
    @JsonIgnore
	private List<UserFavorite> userFavorites;

    @OneToMany(mappedBy = "resourceId", cascade = {CascadeType.REMOVE})
	private List<ResourceFile> resourceFiles;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(new Date().getTime());
        updatedAt = createdAt;
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(new Date().getTime());
    }

    public Resource() {}

    public Resource(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Resource(String title, String description, int clickCount, double price, User user,
            ResourceType resourceType) {
        this.title = title;
        this.description = description;
        this.clickCount = clickCount;
        this.price = price;
        this.user = user;
        this.resourceType = resourceType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
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

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
}
