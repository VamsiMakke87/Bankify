package com.bankify.transaction.service;


import com.bankify.transaction.dto.TransactionDTO;
import com.bankify.transaction.entity.Transactions;
import com.bankify.transaction.repo.RequestRepo;
import com.bankify.transaction.repo.TransactionsRepo;
import com.bankify.transaction.util.BankType;
import com.bankify.transaction.util.TransactionType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepo transactionsRepo;

    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    KafkaTemplate<Object, Object> kafkaTemplate;

    Map<BankType, String> checkBalanceURL;

    public TransactionsService() {
        checkBalanceURL = new HashMap<>();
        checkBalanceURL.put(BankType.BANK1, "http://localhost:8082/bank1/getBalance/");
        checkBalanceURL.put(BankType.BANK2, "http://localhost:8083/bank2/getBalance/");
    }


    @Transactional

    public Map<String, Object> doTransaction(TransactionDTO transactionDTO) {

        try {
            System.out.println(transactionDTO);
            if (transactionDTO.getTransactionType() == TransactionType.DEPOSIT) {
                String URL = checkBalanceURL.get(transactionDTO.getVendorBankType()) + transactionDTO.getVendorEmail();
                System.out.println(URL);
                Map<String,Object> map = (Map<String, Object>) restTemplate.getForObject(URL, HashMap.class);


                if (transactionDTO.getAmount() >(double) map.get("balance")) {
                    return Map.of("Message", "Insufficient funds in vendor account", "isSuccess", false);
                }

                Transactions transaction = new Transactions(transactionDTO);

                transaction = transactionsRepo.save(transaction);

                kafkaTemplate.send("DO-TXN", Map.of("transactionId", transaction.getId(), "user1BankType", transactionDTO.getUserBankType(), "user2BankType", transactionDTO.getVendorBankType(), "user1Email", transactionDTO.getUserEmail(), "user2Email", transactionDTO.getVendorEmail(), "amount", transactionDTO.getAmount()));

                requestRepo.deleteByCode(transactionDTO.getCode());


            } else {
                String URL = checkBalanceURL.get(transactionDTO.getUserBankType()) + transactionDTO.getUserEmail();
                System.out.println(URL);
                Map<String,Object> map = (Map<String, Object>) restTemplate.getForObject(URL, HashMap.class);

                if (transactionDTO.getAmount() >(double) map.get("balance")) {
                    return Map.of("Message", "Insufficient funds in user account", "isSuccess", false);
                }

                Transactions transaction = new Transactions(transactionDTO);

                transaction = transactionsRepo.save(transaction);

                kafkaTemplate.send("DO-TXN", Map.of("transactionId", transaction.getId(), "user1BankType", transactionDTO.getVendorBankType(), "user2BankType", transactionDTO.getUserBankType(), "user1Email", transactionDTO.getVendorEmail(), "user2Email", transactionDTO.getUserEmail(), "amount", transactionDTO.getAmount()));

                requestRepo.deleteByCode(transactionDTO.getCode());


            }

            return Map.of("message", "Transcation in progress", "isSuccess", true);


        } catch (Exception e) {
            System.out.println(e);
            return Map.of("Message", "Error occured, transaction failed", "isSuccess", false);
        }

    }

    public List<Transactions> getTransactions(String email){
        return transactionsRepo.findByUserEmailOrVendorEmail(email,email);
    }

}

