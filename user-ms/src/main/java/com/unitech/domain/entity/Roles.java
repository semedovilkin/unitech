package com.unitech.domain.entity;

import com.unitech.domain.enumeration.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "roles")
public class Roles extends AbstractEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic(optional = false)
    @Column(name = "name")
    private UserRoleEnum name;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public static List<Roles> mockRoleByUser(User user) {
        return Collections.singletonList(
                new Roles()
                        .setName(UserRoleEnum.USER)
                        .setActive(Boolean.TRUE)
                        .setUser(user));
    }

}