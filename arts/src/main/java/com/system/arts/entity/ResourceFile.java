package com.system.arts.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ResourceFiles")
public class ResourceFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "path")
    private String path;
    
    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_resource_id"))
    private Resource resource;

    public ResourceFile() {}

    public ResourceFile(String path, Resource resource) {
        this.path = path;
        this.resource = resource;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
