package org.sid.sec_service.web;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping()
    public ResponseEntity<String> hello()
    {
        return ResponseEntity.ok("hello from secured endpoint");

    }
}
