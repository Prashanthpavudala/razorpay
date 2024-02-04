package com.learning.razorpay.service;

import com.learning.razorpay.dto.ContactDTO;
import org.springframework.http.ResponseEntity;

public interface ContactService {

    ResponseEntity<?> createContact(ContactDTO contactDTO);

    ResponseEntity<?> updateContact(ContactDTO contactDTO, String contactId);

    ResponseEntity<?> fetchAllContacts();

    ResponseEntity<?> fetchContactById(String contactId);

    ResponseEntity<?> updateContactStatus(String contactId, Boolean isActive);
}
