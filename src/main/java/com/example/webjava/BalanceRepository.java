package com.example.webjava;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BalanceRepository {
    private final Map<Long, BigDecimal> storage = new HashMap<>(Map.of(1L, BigDecimal.TEN));

    public BigDecimal getBalanceForId(Long accId) {
        return storage.get(accId);
    }


    public void save(Long id, BigDecimal amount) {
        storage.put(id, amount);
    }
}
