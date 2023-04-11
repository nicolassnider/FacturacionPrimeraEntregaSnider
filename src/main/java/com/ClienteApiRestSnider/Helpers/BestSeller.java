package com.ClienteApiRestSnider.Helpers;

import lombok.Data;

@Data
public class BestSeller {
    private String code;
    private String description;
    private Integer quantity;

    public BestSeller(String code, String description, Integer quantity) {
        this.code = code;
        this.description = description;
        this.quantity = quantity;
    }
}
