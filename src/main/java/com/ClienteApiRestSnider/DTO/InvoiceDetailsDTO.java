package com.ClienteApiRestSnider.DTO;

import lombok.Data;

@Data
public class InvoiceDetailsDTO {
    private Long id;
    private int amoun;
    private double unitPrice;
    private ProductDTO product;


}
