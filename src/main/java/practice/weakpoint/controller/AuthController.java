package practice.weakpoint.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@Tag(name = "Auth", description = "Auth API Document")
public class AuthController {

    @GetMapping("/test")
    @Operation(summary = "Test API", description = "Testing Swagger Configuration.")
    public String testSwagger( ) {
        return "Ok";
    }

}
