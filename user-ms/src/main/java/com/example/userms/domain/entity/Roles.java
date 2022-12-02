package com.example.userms.domain.entity;

import com.example.userms.domain.enumeration.UserRoleEnum;
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
    @GeneratedValue(generator = "role_seq_generator")
    @SequenceGenerator(name = "role_seq_generator", sequenceName = "role_seq", allocationSize = 1)
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