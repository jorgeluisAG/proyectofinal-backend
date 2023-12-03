package taller.grado.proyectofinalbackend.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import taller.grado.proyectofinalbackend.model.*;
import taller.grado.proyectofinalbackend.model.dao.ProductRequest;
import taller.grado.proyectofinalbackend.model.dto.ProductColorResponse;
import taller.grado.proyectofinalbackend.model.dto.ProductResponse;
import taller.grado.proyectofinalbackend.model.dto.ProductSeriesResponse;
import taller.grado.proyectofinalbackend.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final Logger log =  LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping("/only")
    public List<Product> getProductListOnly() {
        log.info("Get List product");
        return productService.getListProductOnly();
    }

    @GetMapping("")
    public List<ProductResponse> getProductList() {
        log.info("Get List product");
        return productService.getListProduct();
    }

    @GetMapping("/store")
    public List<ProductResponse> getProductStoreList() {
        log.info("Get List product");
        return productService.getListProductStore();
    }

    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable(name = "productId") Integer productId){
        log.info("Get List productId: {} " +  productId);
        return productService.getProductById(productId);
    }

    @PostMapping("/confirm")
    public Product createProduct(@RequestBody ProductRequest productRequest, HttpServletRequest request){
        Product product = productService.createProduct(productRequest);
        return product;
    }

    @PutMapping("/change/")
    public Product updateProduct(@RequestBody ProductRequest productRequest, HttpServletRequest request){
        Product product = productService.updateProduct(productRequest);
        return product;
    }

    @GetMapping("/color/{productColorId}")
    public List<ProductColorResponse> getProductsColorById(@PathVariable(name = "productColorId") Integer productColorId){
        log.info("Get List productColorId: {} " , productColorId);
        return productService.getProductsColorById(productColorId);
    }

    @GetMapping("/category/different/{productId}")
    public List<Category> getProductCategoryById(@PathVariable(name = "productId") Integer productId){
        log.info("Get List productCategoryId: {} ",  productId);
        return productService.getProductCategory(productId);
    }

    @GetMapping("/series/{productSeriesId}")
    public List<ProductSeriesResponse> getProductsSeriesById(@PathVariable(name = "productSeriesId") Integer productSeriesId){
        log.info("Get List productColorId: {} " , productSeriesId);
        return productService.getProductsSeriesById(productSeriesId);
    }

    @GetMapping("/images/{productId}")
    public List<ProductImages> getProductsImagesById(@PathVariable(name = "productId") Integer productId){
        log.info("Get List productImagesId: {} " , productId);
        return productService.getProductsImagesById(productId);

    }

    @PutMapping("/image/update")
    public ProductImages updateProductImage(@RequestBody ProductImages productImages){
        ProductImages productImagesOne = productService.updateProductImage(productImages);
        return productImagesOne;
    }

    @DeleteMapping("/deleted/{productId}")
    public Product deleteProductById(@PathVariable(name = "productId") Integer productId){
        return productService.deleteProductById(productId);
    }

    @GetMapping("/series/all")
    public List<AluminumSeries> getAluminumSeriesList() {
        log.info("Get List product series");
        return productService.getAluminumSeriesList();
    }

    @GetMapping("/colors/all")
    public List<AlumColors> getAlumColorsList() {
        log.info("Get List product colors");
        return productService.getAlumColorsList();
    }

    @PutMapping("/color/add")
    public ProductColorResponse updateProductIdColorAdd(@RequestBody ProductColorResponse productColorResponse){
        return productService.updateProductIdColorAdd(productColorResponse);
    }
}




















