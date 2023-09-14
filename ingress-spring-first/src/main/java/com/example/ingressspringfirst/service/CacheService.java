package com.example.ingressspringfirst.service;


import com.example.ingressspringfirst.entity.AccountEntity;
import com.example.ingressspringfirst.repository.AccountRepository;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "account")
public class CacheService {
    private final AccountRepository accountRepository;

    @Cacheable
    @SneakyThrows
    public AccountEntity  test() {
       return accountRepository.findById(1L).get();
    }
    @CachePut
    public AccountEntity  updateCache() {
        return accountRepository.findById(1L).get();
    }

    @CacheEvict(allEntries = true)
    public AccountEntity  removeCache() {
        return accountRepository.findById(1L).get();
    }


}
