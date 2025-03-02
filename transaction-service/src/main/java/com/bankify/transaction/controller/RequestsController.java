package com.bankify.transaction.controller;


import com.bankify.transaction.dto.RequestDTO;
import com.bankify.transaction.entity.Requests;
import com.bankify.transaction.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/requests")
public class RequestsController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/addRequest")
    public ResponseEntity<Requests> addRequest(@RequestBody Requests requests){

        return ResponseEntity.ok(requestService.addRequest(requests));

    }

    @GetMapping("/{code}")
    public ResponseEntity<RequestDTO> findByCode(@PathVariable  String code){
        return ResponseEntity.ok(requestService.findByCode(code));
    }

}
