package com.learning.razorpay.service.impl;

import com.learning.razorpay.dto.ContactDTO;
import com.learning.razorpay.service.ContactService;
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

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    @Value("${razorpay.url}")
    private String RAZORPAY_URL;

    @Autowired
    private Helper helper;

    @Override
    public ResponseEntity<?> createContact(ContactDTO contactDTO) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(RAZORPAY_URL + "/contacts");
            log.info("Final Url for creating contact is {}", uri);
            RequestEntity<?> requestEntity = new RequestEntity<>(contactDTO, helper.getHeaders(), HttpMethod.POST, uri);
            ResponseEntity<?> response = restTemplate.exchange(requestEntity, Object.class);
            if(response.getStatusCode() == HttpStatus.CREATED && response.getBody() != null) {
                log.info("New Contact Created Successfully!!");
                return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);
            } else if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                log.info("Contact Already Exists!!");
                return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
            }
            return response;
        }catch (Exception e) {
            log.error("Exception occurred in createContact is ", e);
            return new ResponseEntity<>(APIConstants.EXCEPTION_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateContact(ContactDTO contactDTO, String contactId) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(RAZORPAY_URL + "/contacts/" + contactId);
            log.info("Final Url for updating contact is {}", uri);
            RequestEntity<?> requestEntity = new RequestEntity<>(contactDTO, helper.getHeaders(), HttpMethod.PATCH, uri);
            return restTemplate.exchange(requestEntity, Object.class);
        } catch (Exception e) {
            log.error("Exception occurred in updateContact is ", e);
            return new ResponseEntity<>(APIConstants.EXCEPTION_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> fetchAllContacts() {
        try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(RAZORPAY_URL + "/contacts");
            log.info("Final Url for fetching all contacts is {}", uri);
            RequestEntity<?> requestEntity = new RequestEntity<>(helper.getHeaders(), HttpMethod.GET, uri);
            return restTemplate.exchange(requestEntity, Object.class);
        } catch (Exception e) {
            log.error("Exception occurred in fetchAllContacts is ", e);
            return new ResponseEntity<>(APIConstants.EXCEPTION_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> fetchContactById(String contactId) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(RAZORPAY_URL + "/contacts/" + contactId);
            log.info("Final Url for fetching contact by id is {}", uri);
            RequestEntity<?> requestEntity = new RequestEntity<>(helper.getHeaders(), HttpMethod.GET, uri);
            return restTemplate.exchange(requestEntity, Object.class);
        } catch (Exception e) {
            log.error("Exception occurred in fetchContactById for {} is ", contactId, e);
            return new ResponseEntity<>(APIConstants.EXCEPTION_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateContactStatus(String contactId, Boolean isActive) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(RAZORPAY_URL + "/contacts/" + contactId);
            log.info("Final Url for updating contact status is {}", uri);
            Map<String, Boolean> body = new HashMap<>();
            body.put("active", isActive);
            RequestEntity<?> requestEntity = new RequestEntity<>(body, helper.getHeaders(), HttpMethod.PATCH, uri);
            return restTemplate.exchange(requestEntity, Object.class);
        } catch (Exception e) {
            log.error("Exception occurred in updateContactStatus for {} is ", contactId, e);
            return new ResponseEntity<>(APIConstants.EXCEPTION_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
