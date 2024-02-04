package com.learning.razorpay.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String contact;
    private String type;
    private String reference_id;
    private Object notes;
}
