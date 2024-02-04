package com.learning.razorpay.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FundByVpaDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private String contact_id;
    private String account_type;
    private VpaDTO vpa;
}
