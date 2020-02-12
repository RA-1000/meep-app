package com.assignment.demo.beans;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Difference that = (Difference) o;
        return exist == that.exist &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exist);
    }
}
