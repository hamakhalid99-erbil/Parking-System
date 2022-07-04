package friendoo.parking_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Parking System")
                        .description("Rest API's")
                        .version("v1.0")
                );
    }
}