package com.unitech.domain.repository;

import com.unitech.domain.entity.Account;
import com.unitech.domain.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    List<Account> findAllByCustomerAndActive(Customer customer, Boolean active);

    @EntityGraph(attributePaths = "customer", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT a FROM Account a WHERE a.accountNo = ?1")
    Optional<Account> findAccountByAccountNo(Integer accountNo);
}
