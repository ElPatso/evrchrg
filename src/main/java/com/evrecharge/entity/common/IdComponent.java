package com.evrecharge.entity.common;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class IdComponent<T extends IdComponent> implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }
}
