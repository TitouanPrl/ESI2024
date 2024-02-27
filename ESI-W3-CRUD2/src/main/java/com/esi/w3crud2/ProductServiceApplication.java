package com.esi.w3crud2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Products - Orders API")
                        .description("Products - Orders API")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Products - Orders Documentation")
                        .url("http://localhost:8080/swagger-ui/4.15.5/index.html"));
    }

    /*
     * @Bean
     * public CommandLineRunner loadData(ProductRepository productRepository) {
     * return args -> {
     * Product product = new Product();
     * product.setId("01");
     * product.setName("light vehicle");
     * product.setDescription("Can also be used for light work");
     * product.setPrice(BigDecimal.valueOf(1233));
     * 
     * productRepository.save(product);
     * };
     * }
     */
}
