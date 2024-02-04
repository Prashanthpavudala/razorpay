package com.learning.razorpay.service.impl;

import com.learning.razorpay.dto.FundByBankAccountDTO;
import com.learning.razorpay.dto.FundByVpaDTO;
import com.learning.razorpay.service.FundService;
import com.learning.razorpay.utils.APIConstants;
import com.learning.razorpay.utils.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class FundServiceImpl implements FundService {

    @Autowired
    private Helper helper;

    @Value("${razorpay.url}")
    private String RAZORPAY_URL;

    @Override
    public ResponseEntity<?> createFundByBankAccount(FundByBankAccountDTO fundByBankAccountDTO) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(RAZORPAY_URL + "/fund_accounts");
            log.info("Final Url for creating FundByBankAccount is {}", uri);
            RequestEntity<?> requestEntity = new RequestEntity<>(fundByBankAccountDTO, helper.getHeaders(), HttpMethod.POST, uri);
            ResponseEntity<?> response = restTemplate.exchange(requestEntity, Object.class);
            if(response.getStatusCode() == HttpStatus.CREATED && response.getBody() != null) {
                log.info("New Fund Created Successfully by Bank Account!!");
                return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);
            } else if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                log.info("Fund Already Exists!!");
                return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
            }
            return response;
        }catch (Exception e) {
            log.error("Exception occurred in createFundByBankAccount is ", e);
            return new ResponseEntity<>(APIConstants.EXCEPTION_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> createFundByVPA(FundByVpaDTO fundByVpaDTO) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(RAZORPAY_URL + "/fund_accounts");
            log.info("Final Url for creating FundByVPA is {}", uri);
            RequestEntity<?> requestEntity = new RequestEntity<>(fundByVpaDTO, helper.getHeaders(), HttpMethod.POST, uri);
            ResponseEntity<?> response = restTemplate.exchange(requestEntity, Object.class);
            if(response.getStatusCode() == HttpStatus.CREATED && response.getBody() != null) {
                log.info("New Fund Created Successfully By VPA!!");
                return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);
            } else if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                log.info("Fund Already Exists!!");
                return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
            }
            return response;
        }catch (Exception e) {
            log.error("Exception occurred in createFundByVPA is ", e);
            return new ResponseEntity<>(APIConstants.EXCEPTION_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> fetchAllFundAccounts() {
        try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(RAZORPAY_URL + "/fund_accounts");
            log.info("Final Url for fetchAllFundAccounts is {}", uri);
            RequestEntity<?> requestEntity = new RequestEntity<>(helper.getHeaders(), HttpMethod.GET, uri);
            return restTemplate.exchange(requestEntity, Object.class);
        }catch (Exception e) {
            log.error("Exception occurred in fetchAllFundAccounts is ", e);
            return new ResponseEntity<>(APIConstants.EXCEPTION_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> fetchFundAccountById(String fundId) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(RAZORPAY_URL + "/fund_accounts/" + fundId);
            log.info("Final Url for fetchFundAccountById is {}", uri);
            RequestEntity<?> requestEntity = new RequestEntity<>(helper.getHeaders(), HttpMethod.GET, uri);
            return restTemplate.exchange(requestEntity, Object.class);
        }catch (Exception e) {
            log.error("Exception occurred in fetchFundAccountById is ", e);
            return new ResponseEntity<>(APIConstants.EXCEPTION_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> changeFundAccountStatus(String fundId, Boolean isActive) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(RAZORPAY_URL + "/fund_accounts/" + fundId);
            Map<String, Boolean> body = new HashMap<>();
            body.put("active", isActive);
            log.info("Final Url for changeFundAccountStatus is {}", uri);
            RequestEntity<?> requestEntity = new RequestEntity<>(body, helper.getHeaders(), HttpMethod.POST   , uri);
            return restTemplate.exchange(requestEntity, Object.class);
        }catch (Exception e) {
            log.error("Exception occurred in changeFundAccountStatus is ", e);
            return new ResponseEntity<>(APIConstants.EXCEPTION_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
