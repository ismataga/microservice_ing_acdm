package com.example.ingressspringfirst.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DictionaryResponse {
    private Long id;
    private String category;
    private String subCategory;
}
