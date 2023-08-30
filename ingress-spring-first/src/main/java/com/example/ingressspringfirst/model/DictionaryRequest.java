package com.example.ingressspringfirst.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryRequest {
    private String category;
    private String subCategory;
}
