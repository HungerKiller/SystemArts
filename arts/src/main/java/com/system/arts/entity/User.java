package com.system.arts.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "username", nullable = false)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "age")
    private Integer age;
    
    @Column(name = "roleType", nullable = false)
    private RoleType roleType;
    
    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
	private List<Resource> resources;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
	private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
	private List<UserFavorite> userFavorites;
    
    public User() {}
    
    public User(String username, String password, String email, Integer age, RoleType roleType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.roleType = roleType;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
