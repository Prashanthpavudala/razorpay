package com.learning.razorpay.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class VpaDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private String address;
}
