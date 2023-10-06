package com.example.webjava;

import com.example.webjava.model.TransferBalance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class BankService {

    private final BalanceRepository repository;

    public BigDecimal getBalance(Long accId) {
        BigDecimal balance = repository.getBalanceForId(accId);
        if (balance == null) {
            throw new IllegalArgumentException();
        }
        return balance;
    }

    public BigDecimal addMoney(Long to, BigDecimal amount) {
        BigDecimal currrntBalance = repository.getBalanceForId(to);
        if (currrntBalance == null) {
            repository.save(to, amount);
            return amount;
        } else {
            BigDecimal updateBalance = currrntBalance.add(amount);
            repository.save(to, updateBalance);
            return updateBalance;
        }
    }

    public void makeTransfer(TransferBalance transferBalance) {
        BigDecimal fromBalance = repository.getBalanceForId(transferBalance.getFrom());
        BigDecimal toBalance = repository.getBalanceForId(transferBalance.getTo());
        if (fromBalance == null || toBalance == null) throw new IllegalArgumentException("no id");
        BigDecimal updateFromBalance = fromBalance.subtract(transferBalance.getAmount());
        BigDecimal updateToBalance = toBalance.add(transferBalance.getAmount());
        repository.save(transferBalance.getFrom(), updateFromBalance);
        repository.save(transferBalance.getTo(), updateToBalance);



    }
}
