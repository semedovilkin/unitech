package com.example.userms.domain.entity;

import com.example.userms.domain.enumeration.UserStatusesEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "user_login")
public class User extends AbstractEntity implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "user_login_seq_generator")
    @SequenceGenerator(name = "user_login_seq_generator", sequenceName = "user_login_seq", allocationSize = 1)
    private long id;
    @Column(name = "pin")
    private String pin;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatusesEnum status;

    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Roles> roles;

}
