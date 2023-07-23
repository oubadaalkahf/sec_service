package org.sid.sec_service.web;


import lombok.RequiredArgsConstructor;
import org.sid.sec_service.entities.AuthenticationRequest;
import org.sid.sec_service.entities.AuthenticationResponse;
import org.sid.sec_service.entities.AuthenticationService;
import org.sid.sec_service.entities.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }


    @PostMapping( "/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest authenticationRequest
    ){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/addRole")
    public ResponseEntity<String> addRole(
            @RequestBody AddRoleToUserRequest addRoleToUserRequest
    ){
        return ResponseEntity.ok(authenticationService.addRole(addRoleToUserRequest));
    }

}
