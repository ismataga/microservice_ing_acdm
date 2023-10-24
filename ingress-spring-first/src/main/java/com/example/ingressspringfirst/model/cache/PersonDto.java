package com.example.ingressspringfirst.model.cache;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto implements Serializable {
    private static final long serialVersionUID =1L;
    private String pin;
    private String address;
}
