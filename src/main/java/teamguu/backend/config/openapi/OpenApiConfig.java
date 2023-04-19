package teamguu.backend.config.openapi;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

//    @Bean
//    public OpenAPI openAPI() {
//        Info info = new Info()
//                .title("TeamGuu API")
//                .version("v1.0.0")
//                .description("for teamguu application frontend");
//
//        return new OpenAPI()
//                .components(new Components())
//                .info(info);
//    }

    @Bean
    public OpenAPI openAPI() {

        final String securitySchemeName = "bearerAuth";

        Info info = new Info()
                .title("TeamGuu API")
                .version("v1.0.0")
                .description("for teamguu application frontend");

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(info);
    }
}