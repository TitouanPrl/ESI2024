package com.esi.w3crud2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import com.esi.w3crud2.products.controller.ProductController;
import com.esi.w3crud2.products.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

/* 
@WebMvcTest annotation that can be used for a Spring MVC test that focuses only on Spring MVC components.
Using this annotation will disable full auto-configuration and instead apply only configuration relevant to MVC tests 
(i.e. @Controller,  @JsonComponent, WebMvcConfigurer  beans but not @Service or @Repository beans). 
*/

//Using OrderController.class as parameter, we are asking to initialize only one web controller
//if we did not specify a Controller.class, it will scan all Controller classes in the application
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@WebMvcTest(ProductController.class)
class ProductControllerTests {
    @MockBean
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProductsApiTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductByIdApiTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/products/{id}", 01)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void postProductApiTest() throws Exception {
        Product product = new Product();
        product.setId("10");
        product.setName("product 10");
        product.setDescription("it cost less");
        product.setPrice(BigDecimal.valueOf(1233));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        // HTTP returns 200 "OK" status when there is a BODY, and
        // return 201 "Created" status when there is BODY.
        // However, we are controlling the returned status code by using
        // @ResponseStatus(HttpStatus.CREATED) = 201
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteProductApiTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/products/{id}", 15))
                .andExpect(status().isOk());
    }

    // to be done by students
    @Test
    void putProductApiTest() throws Exception {

    }
}
