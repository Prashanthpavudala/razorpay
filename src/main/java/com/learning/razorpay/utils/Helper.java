package com.learning.razorpay.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class Helper {

    @Value("${razorpay.key.id}")
    private String RAZORPAY_KEY;

    @Value("${razorpay.key.secret}")
    private String RAZORPAY_SECRET;

    public HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(RAZORPAY_KEY, RAZORPAY_SECRET);
        return headers;
    }
}
