package taller.grado.proyectofinalbackend.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import taller.grado.proyectofinalbackend.model.*;
import taller.grado.proyectofinalbackend.model.dao.ProductRequest;
import taller.grado.proyectofinalbackend.model.dto.ProductColorResponse;
import taller.grado.proyectofinalbackend.model.dto.ProductResponse;
import taller.grado.proyectofinalbackend.repository.AlumColorsRepository;
import taller.grado.proyectofinalbackend.repository.CategoryRepository;
import taller.grado.proyectofinalbackend.repository.ProductColorRepository;
import taller.grado.proyectofinalbackend.repository.ProductRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductColorRepository productColorRepository;
    private AlumColorsRepository alumColorsRepository;



    public Product getProduct(Integer productId){

        return productRepository.findById(productId).orElse(null);

    }

    public Product getProductById(Integer productId){

        Product product = productRepository.findById(productId).orElse(null);
        ProductResponse productResponse = new ProductResponse();

        productResponse.setId(productId);
        productResponse.setNameProduct(product.getNameProduct());
        productResponse.setSeriesProduct(product.getSeriesProduct());
        productResponse.setImageProduct(product.getImageProduct());
        productResponse.setDescriptionProduct(product.getDescriptionProduct());
        productResponse.setStockTotal(product.getStockTotal());
        productResponse.setPrice(product.getPrice());
        productResponse.setState(product.getState());
        Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
        productResponse.setCategory(category);
        log.info("DATO HECHOS "+productResponse);
        log.info("DATO CAtego "+category.getId());
        return product;

    }

    public List<Product> getListProduct() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequest productRequest){

        Product product = new Product();
        product.setNameProduct(productRequest.getNameProduct());
        product.setSeriesProduct(productRequest.getSeriesProduct());
        product.setImageProduct(productRequest.getImageProduct());
        product.setDescriptionProduct(productRequest.getDescriptionProduct());
        product.setStockTotal(productRequest.getStockTotal());
        product.setPrice(productRequest.getPrice());
        product.setState(productRequest.getState());

        Category category = categoryRepository.findById(productRequest.getCategoryId()).get();
        product.setCategory(category);
        Product productNew = productRepository.save(product);
        int j=productNew.getStockTotal();
        //log.info("DATO J "+ j);
        //log.info("DATO ProductNew "+productNew.getStockTotal());
        for(int i=0;i<productRequest.getAlumColorStockRequests().size();i++){
            AlumColors alumColors = alumColorsRepository.findById(productRequest.getAlumColorStockRequests().get(i).getAlumColorId()).get();
            ProductColor productColor = new ProductColor();
            productColor.setProduct(productNew);
            productColor.setAlumColor(alumColors);
            productColor.setStockColor(productRequest.getAlumColorStockRequests().get(i).getStockColor());
            j= j+ productRequest.getAlumColorStockRequests().get(i).getStockColor();
            productColorRepository.save(productColor);
        }
        //log.info("DATO J2 "+ j);
        productNew.setStockTotal(j);
        productRepository.save(productNew);

        //log.info("DATOS OBTenidossssssssss",productNew);
        return productNew;
    }



    public List<Product> getListProductAll(){
        List<Product> products = productRepository.findAll();
//        for(int i=0;i<products.size();i++){
//            byte[] imageByte = products.get(i).getImageProduct();
//            byte[] encodedImage = Base64.encodeBase64(imageByte);
//            Product product = products.get(i);
//            products.set(i,product).setImageProduct(encodedImage);
//        }
        return products;
    }

    public Product createProductFail(ProductRequest productRequest){
        //Category category = categoryRepository.save(productRequest.getCategory());
        //ProductColor productColor = productColorRepository.save(productRequest.getProductColor());

        Product product = new Product();
        product.setNameProduct(productRequest.getNameProduct());
        //product.setCodeProduct(productRequest.getCodeProduct());
        product.setImageProduct(productRequest.getImageProduct());
        product.setDescriptionProduct(productRequest.getDescriptionProduct());
        //product.setStock(productRequest.getStock());
        product.setPrice(productRequest.getPrice());
        product.setState(productRequest.getState());
        //product.setCategory(category);
        //product.setProductColor(productColor);
        Product productNew = productRepository.save(product);
        //log.info("DATOS OBTenidossssssssss",productNew);
        return productNew;
    }

    public Product updateProduct(ProductRequest productRequest){
        //Category category = categoryRepository.save(productRequest.getCategory());
        //ProductColor productColor = productColorRepository.save(productRequest.getProductColor());


        Product product = productRepository.findById(productRequest.getId()).get();
        product.setId(productRequest.getId());
        product.setNameProduct(productRequest.getNameProduct());
        //product.setCodeProduct(productRequest.getCodeProduct());
        product.setImageProduct(productRequest.getImageProduct());
        product.setDescriptionProduct(productRequest.getDescriptionProduct());
        //product.setStock(productRequest.getStock());
        product.setPrice(productRequest.getPrice());
        product.setState(productRequest.getState());
        //product.setCategory(category);
        //product.setProductColor(productColor);
        Product productNew = productRepository.save(product);
        //log.info("DATOS OBTenidossssssssss",productNew);
        return productNew;
    }

    public List<ProductColorResponse> getProductColorById(Integer productColorId){

        return productColorRepository.getProductColorsById(productColorId);

    }


}
