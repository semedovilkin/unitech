package com.example.accountms.domain.entity;

import com.example.accountms.domain.enumeration.AccountStatusEnum;
import com.example.accountms.domain.enumeration.Currency;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "user_account",schema = "public")
public class UserAccount extends AbstractEntity implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "account_seq_generator")
    @SequenceGenerator(name = "account_seq_generator", sequenceName = "account_seq", allocationSize = 1)
    private Long id;

    @Column(name = "account_number")
    private String accountNo;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
}

