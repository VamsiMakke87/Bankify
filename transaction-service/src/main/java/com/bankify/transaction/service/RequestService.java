package com.bankify.transaction.service;


import com.bankify.transaction.dto.RequestDTO;
import com.bankify.transaction.entity.Requests;
import com.bankify.transaction.repo.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;

@Service
public class RequestService {

    @Autowired
    private RequestRepo repo;

    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";





    public Requests addRequest(Requests requests){

        requests.setCode(generateUniqueId(8));

      return repo.save(requests);

    }

    public  String generateUniqueId(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }

    public RequestDTO findByCode(String code){
        Requests request = repo.findByCode(code);
        if(request==null)return null;
        return new RequestDTO(request);
    }


}
