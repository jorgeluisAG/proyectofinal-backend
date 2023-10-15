package taller.grado.proyectofinalbackend.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import taller.grado.proyectofinalbackend.model.Product;
import taller.grado.proyectofinalbackend.model.dao.ProductRequest;
import taller.grado.proyectofinalbackend.model.dto.ProductColorResponse;
import taller.grado.proyectofinalbackend.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final Logger log =  LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping("")
    public List<Product> getProductList() {
        log.info("Get List product");
        return productService.getListProduct();
    }

    @GetMapping("{productId}")
    public Product getProductById(@PathVariable(name = "productId") Integer productId){
        log.info("Get List productId: {} " +  productId);
        return productService.getProductById(productId);
    }

    @PostMapping("/confirm/")
    public Product createProduct(@RequestBody ProductRequest productRequest, HttpServletRequest request){
        Product product = productService.createProduct(productRequest);
        return product;
    }

    @PostMapping("/change/")
    public Product updateProduct(@RequestBody ProductRequest productRequest, HttpServletRequest request){
        Product product = productService.updateProduct(productRequest);
        return product;
    }

    @GetMapping("/productColor/{productColorId}")
    public List<ProductColorResponse> getProductColorById(@PathVariable(name = "productColorId") Integer productColorId){
        log.info("Get List productId: {} " +  productColorId);
        return productService.getProductColorById(productColorId);
    }
}




















