package com.example.ingressspringfirst.model.jwt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessTokenClaimsSet  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String iss;
    @JsonProperty("exp")
    private Date expirationTime;
    @JsonProperty("iat")
    private Date createdTime;

}
