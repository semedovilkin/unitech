package com.unitech.config;

import com.unitech.domain.entity.AbstractEntity;
import com.unitech.domain.enumeration.EntityStateEnum;

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