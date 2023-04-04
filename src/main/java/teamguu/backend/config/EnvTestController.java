package teamguu.backend.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping(value = "/api/test")
@Tag(name = "Env Test", description = "Env Test API Document")
public class EnvTestController {

    @Value("${spring.cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    @Operation(summary = "Env Test API", description = "just get your aws env")
    @ResponseStatus(OK)
    @PostMapping("/aws")
    public String envTest() {
        return awsAccessKey;
    }
}
