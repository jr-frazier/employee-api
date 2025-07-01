package com.luv2code.springboot.employee.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Employee API", version = "1.0"), security = @SecurityRequirement(name = "basicAuth"))
@SecurityScheme(name="basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class SwaggerConfig {

}
