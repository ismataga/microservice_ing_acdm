package com.example.ingressspringfirst.model.jwt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefreshTokenClaimsSet  implements Serializable {

    private Long userId;
    private Date expirationTime;
    private Integer count;
    private String iss;
}
