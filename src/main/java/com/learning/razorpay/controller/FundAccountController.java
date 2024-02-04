package com.learning.razorpay.controller;

import com.learning.razorpay.dto.FundByBankAccountDTO;
import com.learning.razorpay.dto.FundByVpaDTO;
import com.learning.razorpay.service.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fund")
@Slf4j
public class FundAccountController {

    @Autowired
    private FundService fundService;

    @PostMapping("/createFundByBankAccount")
    public ResponseEntity<?> createFundByBankAccount(@RequestBody FundByBankAccountDTO fundByBankAccountDTO) {
        log.info("Inside createFundByBankAccount with request {}", fundByBankAccountDTO);
        return fundService.createFundByBankAccount(fundByBankAccountDTO);
    }

    @PostMapping("/createFundByVPA")
    public ResponseEntity<?> createFundByVPA(@RequestBody FundByVpaDTO fundByVpaDTO) {
        log.info("Inside createFundByVPA with request {}", fundByVpaDTO);
        return fundService.createFundByVPA(fundByVpaDTO);
    }

    @GetMapping("fetchAllFundAccounts")
    public ResponseEntity<?> fetchAllFundAccounts() {
        log.info("Inside fetchAllFundAccounts!!!");
        return fundService.fetchAllFundAccounts();
    }

    @GetMapping("/fetchFundAccountById")
    public ResponseEntity<?> fetchFundAccountById(@RequestParam("fundId") String fundId) {
        log.info("Inside fetchFundAccountById for id {}", fundId);
        return fundService.fetchFundAccountById(fundId);
    }

    @PatchMapping("/changeFundAccountStatus")
    public ResponseEntity<?> changeFundAccountStatus(
            @RequestParam("fundId") String fundId,
            @RequestParam("isActive") Boolean isActive) {
        log.info("Inside changeFundAccountStatus for id {} and status {}", fundId, isActive);
        return fundService.changeFundAccountStatus(fundId, isActive);
    }

}
