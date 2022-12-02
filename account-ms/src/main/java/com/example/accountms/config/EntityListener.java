package com.example.accountms.config;

import com.example.accountms.domain.entity.AbstractEntity;
import com.example.accountms.domain.enumeration.EntityStateEnum;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class EntityListener {

    @PrePersist
    public void onPersist(AbstractEntity abstractEntity) {
        abstractEntity.setCreatedDate(new Date());
        abstractEntity.setState(EntityStateEnum.ACTIVE);
    }

    @PreUpdate
    public void onUpdate(AbstractEntity abstractEntity) {
        abstractEntity.setState(EntityStateEnum.UPDATED);
        abstractEntity.setUpdatedDate(new Date());
    }
}