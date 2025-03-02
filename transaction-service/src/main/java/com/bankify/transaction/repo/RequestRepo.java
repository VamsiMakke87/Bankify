package com.bankify.transaction.repo;

import com.bankify.transaction.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<Requests,Long> {
    Requests findByCode(String code);

    Long deleteByCode(String code);
}
