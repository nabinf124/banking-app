package com.budhathoki.edu.banking_app.repository;

import com.budhathoki.edu.banking_app.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
