package com.learning.razorpay.service;

import com.learning.razorpay.dto.FundByBankAccountDTO;
import com.learning.razorpay.dto.FundByVpaDTO;
import org.springframework.http.ResponseEntity;

public interface FundService {
    ResponseEntity<?> createFundByBankAccount(FundByBankAccountDTO fundByBankAccountDTO);

    ResponseEntity<?> createFundByVPA(FundByVpaDTO fundByVpaDTO);

    ResponseEntity<?> fetchAllFundAccounts();

    ResponseEntity<?> fetchFundAccountById(String fundId);

    ResponseEntity<?> changeFundAccountStatus(String fundId, Boolean isActive);
}
