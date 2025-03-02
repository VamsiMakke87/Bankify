package com.bankify.transaction.controller;

import com.bankify.transaction.dto.TransactionDTO;
import com.bankify.transaction.entity.Transactions;
import com.bankify.transaction.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/doTransaction")
    public ResponseEntity<Map<String,Object>> doTransaction(@RequestBody TransactionDTO transactionDTO){
        return ResponseEntity.ok(transactionsService.doTransaction(transactionDTO));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Transactions>> getTransactions(@PathVariable String email){
        return ResponseEntity.ok(transactionsService.getTransactions(email));
    }

}
