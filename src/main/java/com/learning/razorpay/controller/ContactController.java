package com.learning.razorpay.controller;

import com.learning.razorpay.dto.ContactDTO;
import com.learning.razorpay.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class ContactController {

    @Autowired
    private ContactService paymentService;

    @PostMapping("/createContact")
    public ResponseEntity<?> createContact(@RequestBody ContactDTO contactDTO) {
        log.info("Inside createContact with request {}", contactDTO);
        return paymentService.createContact(contactDTO);
    }

    @PatchMapping("/updateContact")
    public ResponseEntity<?> updateContact(
            @RequestParam("contactId") String contactId,
            @RequestBody ContactDTO contactDTO) {
        log.info("Inside updateContact for id {} with request {}", contactId, contactDTO);
        return paymentService.updateContact(contactDTO, contactId);
    }

    @GetMapping("/fetchAllContacts")
    public ResponseEntity<?> fetchAllContacts() {
        log.info("Inside fetchAllContacts");
        return paymentService.fetchAllContacts();
    }

    @GetMapping("/fetchContactById")
    public ResponseEntity<?> fetchContactById(@RequestParam("contactId") String contactId) {
        log.info("Inside fetchContactById with request {}", contactId);
        return paymentService.fetchContactById(contactId);
    }

    @PatchMapping("/updateContactStatus")
    public ResponseEntity<?> updateContactStatus(
            @RequestParam("contactId") String contactId,
            @RequestParam("isActive") Boolean isActive) {
        log.info("Inside updateContactStatus for id {} and status {}", contactId, isActive);
        return paymentService.updateContactStatus(contactId, isActive);
    }
}
