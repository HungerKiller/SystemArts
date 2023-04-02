package com.system.arts.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UserFavorites")
public class UserFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_user_id"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_resource_id"))
    private Resource resource;

    public UserFavorite() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
