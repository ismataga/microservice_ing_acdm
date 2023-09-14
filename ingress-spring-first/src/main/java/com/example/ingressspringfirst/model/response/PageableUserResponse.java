package com.example.ingressspringfirst.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableUserResponse {
    private List<UserResponse> users;
    private int lastPageNumber;
    private long totalElement;
    private boolean hasNextPage;
}
