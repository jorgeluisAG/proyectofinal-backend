package taller.grado.proyectofinalbackend.service;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import taller.grado.proyectofinalbackend.model.Category;
import taller.grado.proyectofinalbackend.model.Product;
import taller.grado.proyectofinalbackend.model.ProductColor;
import taller.grado.proyectofinalbackend.model.dao.ProductRequest;
import taller.grado.proyectofinalbackend.repository.CategoryRepository;
import taller.grado.proyectofinalbackend.repository.ProductColorRepository;
import taller.grado.proyectofinalbackend.repository.ProductRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductColorRepository productColorRepository;

    public Product getProduct(Integer productId){
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> getListProduct(){
        List<Product> products = productRepository.findAll();
//        for(int i=0;i<products.size();i++){
//            byte[] imageByte = products.get(i).getImageProduct();
//            byte[] encodedImage = Base64.encodeBase64(imageByte);
//            Product product = products.get(i);
//            products.set(i,product).setImageProduct(encodedImage);
//        }
        return products;
    }

    public Product createProduct(ProductRequest productRequest){
        Category category = categoryRepository.save(productRequest.getCategory());
        ProductColor productColor = productColorRepository.save(productRequest.getProductColor());

        Product product = new Product();
        product.setNameProduct(productRequest.getNameProduct());
        //product.setCodeProduct(productRequest.getCodeProduct());
        product.setImageProduct(productRequest.getImageProduct());
        product.setDescriptionProduct(productRequest.getDescriptionProduct());
        //product.setStock(productRequest.getStock());
        product.setPrice(productRequest.getPrice());
        product.setState(productRequest.getState());
        product.setCategory(category);
        //product.setProductColor(productColor);
        Product productNew = productRepository.save(product);
        log.info("DATOS OBTenidossssssssss",productNew);
        return productNew;
    }

    public Product updateProduct(ProductRequest productRequest){
        Category category = categoryRepository.save(productRequest.getCategory());
        ProductColor productColor = productColorRepository.save(productRequest.getProductColor());


        Product product = productRepository.findById(productRequest.getId()).get();
        product.setId(productRequest.getId());
        product.setNameProduct(productRequest.getNameProduct());
        //product.setCodeProduct(productRequest.getCodeProduct());
        product.setImageProduct(productRequest.getImageProduct());
        product.setDescriptionProduct(productRequest.getDescriptionProduct());
        //product.setStock(productRequest.getStock());
        product.setPrice(productRequest.getPrice());
        product.setState(productRequest.getState());
        product.setCategory(category);
        //product.setProductColor(productColor);
        Product productNew = productRepository.save(product);
        log.info("DATOS OBTenidossssssssss",productNew);
        return productNew;
    }


}
