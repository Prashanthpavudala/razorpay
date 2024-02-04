package com.learning.razorpay.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private String name;
    private String ifsc;
    private String account_number;
}
