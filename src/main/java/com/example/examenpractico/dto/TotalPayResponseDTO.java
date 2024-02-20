package com.example.examenpractico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalPayResponseDTO {

    private Double payment;
    private boolean success;
}
