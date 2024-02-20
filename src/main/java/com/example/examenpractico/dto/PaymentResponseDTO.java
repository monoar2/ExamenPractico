package com.example.examenpractico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponseDTO {

    private double amount;
    private boolean success;
}
