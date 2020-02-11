package com.assignment.demo.beans;

import javax.persistence.*;

@Entity
@Table(name = "diferences")
public class Difference {
    @Id
    @Column(name = "id", length = 240, nullable = false)
    private String id;

    @Column(name = "exist", nullable = false)
    private boolean exist;

    public Difference() {
    }

    public Difference(String id, boolean exist) {
        this.id = id;
        this.exist = exist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
