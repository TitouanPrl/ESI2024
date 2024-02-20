package com.esi.week2.products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

private  List<Product> products =  new ArrayList<>(Arrays.asList(
       new Product("01", "Heavy vehicle", "Can be used for heavy work", BigDecimal.valueOf(1200)),
       new Product("02", "Medium vehicle", "Can be used for medium work", BigDecimal.valueOf(1800)),
       new Product("03", "Light vehicle", "Can be used for light work", BigDecimal.valueOf(2200)) 
    ));
}
