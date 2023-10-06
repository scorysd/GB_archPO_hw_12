package com.example.webjava;

import com.example.webjava.model.TransferBalance;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController("/balance")
@AllArgsConstructor
public class BalanceController {

    private final BankService bankService;

    @GetMapping("/{accId}")
    public BigDecimal getBalance(@PathVariable Long accId) {
       return bankService.getBalance(accId);
    }

    @PostMapping("/add")
    public BigDecimal addMoney(@RequestBody TransferBalance transferBalance) {
        return bankService.addMoney(transferBalance.getTo(), transferBalance.getAmount());
    }

    @PostMapping("/transfer")
    public void moneyTrans(@RequestBody TransferBalance transferBalance) {
        bankService.makeTransfer(transferBalance);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e){
        log.error(e.getMessage());
        return "что-то пошло не так...";
    }
}
