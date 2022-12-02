package com.example.accountms.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "users",schema = "public")
@Where(clause = "state <> 0")
public class User extends AbstractEntity implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "user_seq_generator")
    @SequenceGenerator(name = "user_seq_generator", sequenceName = "user_seq", allocationSize = 1)
    private long id;

    @Column(name = "pin")
    private String pin;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<UserAccount> accounts;

}
