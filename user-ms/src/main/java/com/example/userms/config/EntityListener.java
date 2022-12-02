package com.example.userms.config;

import com.example.userms.domain.entity.AbstractEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class EntityListener {

    @PrePersist
    public void onPersist(AbstractEntity abstractEntity) {
        abstractEntity.setCreatedDate(new Date());
    }

    @PreUpdate
    public void onUpdate(AbstractEntity abstractEntity) {
        abstractEntity.setUpdatedDate(new Date());
    }
}