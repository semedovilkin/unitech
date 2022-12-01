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
public class User implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;
    @Column(name = "pin")
    private String pin;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private UserStatusesEnum status;

    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Roles> roles;

}
