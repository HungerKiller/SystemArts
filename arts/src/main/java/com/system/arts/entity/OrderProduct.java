package com.system.arts.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderProducts")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_resource_id"))
    private Resource resource;

    public OrderProduct() {}

    public OrderProduct(int orderId, Resource resource) {
        this.orderId = orderId;
        this.resource = resource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
