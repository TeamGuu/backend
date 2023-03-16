package practice.weakpoint.config.openapi;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("WeakPoint API")
                .version("v1.0.0")
                .description("for WeakPoint");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}