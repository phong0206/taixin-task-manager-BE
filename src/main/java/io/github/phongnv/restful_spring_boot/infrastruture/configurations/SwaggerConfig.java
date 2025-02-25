package io.github.phongnv.restful_spring_boot.infrastruture.configurations;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Task Manager",
                version = "v1",
                description = "API documentation for My Application"
        )
)
public class SwaggerConfig {
}
