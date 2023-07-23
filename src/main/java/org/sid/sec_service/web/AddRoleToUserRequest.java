package org.sid.sec_service.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleToUserRequest {
    private String role;
    private String phoneNumber;
}
