package com.system.arts.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

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

    @OneToMany(mappedBy = "resource", cascade = {CascadeType.REMOVE})
	private List<Comment> comments;

    @OneToMany(mappedBy = "resource", cascade = {CascadeType.REMOVE})
	private List<UserFavorite> userFavorites;

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

    public Resource(String title, String address, String description, double price) {
        this.title = title;
        this.address = address;
        this.description = description;
        this.price = price;
    }

    public Resource(String title, String address, String description, double price, User user,
            ResourceType resourceType) {
        this.title = title;
        this.address = address;
        this.description = description;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
