package com.example.ingressspringfirst.repository;

import com.example.ingressspringfirst.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity,Long> {
}
