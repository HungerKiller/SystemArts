package com.system.arts.dto;

import java.sql.Timestamp;
import java.util.List;

import com.system.arts.entity.Comment;
import com.system.arts.entity.ResourceFile;
import com.system.arts.entity.ResourceType;
import com.system.arts.entity.User;
import com.system.arts.entity.UserFavorite;

import lombok.Data;

@Data
public class ResourceDto {

    private int id;
    private String title;
    private String description;
    private int clickCount;
    private double price;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private User user;
    private ResourceType resourceType;
	private List<Comment> comments;
	private List<UserFavorite> userFavorites;
	private List<ResourceFile> resourceFiles;

    public ResourceDto() {}

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


    public List<Comment> getComments() {
        return comments;
    }


    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public List<UserFavorite> getUserFavorites() {
        return userFavorites;
    }


    public void setUserFavorites(List<UserFavorite> userFavorites) {
        this.userFavorites = userFavorites;
    }


    public List<ResourceFile> getResourceFiles() {
        return resourceFiles;
    }


    public void setResourceFiles(List<ResourceFile> resourceFiles) {
        this.resourceFiles = resourceFiles;
    }



}
