package taller.grado.proyectofinalbackend.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import taller.grado.proyectofinalbackend.model.*;
import taller.grado.proyectofinalbackend.model.dao.AlumColorStockRequest;
import taller.grado.proyectofinalbackend.model.dao.ProductRequest;
import taller.grado.proyectofinalbackend.model.dto.ProductColorResponse;
import taller.grado.proyectofinalbackend.model.dto.ProductResponse;
import taller.grado.proyectofinalbackend.model.dto.ProductSeriesResponse;
import taller.grado.proyectofinalbackend.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {
    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductColorRepository productColorRepository;
    private AlumColorsRepository alumColorsRepository;
    private AluminumSeriesRepository aluminumSeriesRepository;
    private ProductAlumSeriesRepository productAlumSeriesRepository;
    private ProductImagesRepository productImagesRepository;
    private ThicknessRepository thicknessRepository;



    public Product getProduct(Integer productId){

        return productRepository.findById(productId).orElse(null);

    }

    public ProductResponse getProductById(Integer productId){

        Product product = productRepository.findById(productId).orElse(null);
        ProductResponse productResponse = new ProductResponse();

        productResponse.setId(productId);
        productResponse.setNameProduct(product.getNameProduct());
        productResponse.setDescriptionProduct(product.getDescriptionProduct());
        productResponse.setStockTotal(product.getStockTotal());
        productResponse.setPrice(product.getPrice());
        productResponse.setState(product.getState());


        List<ProductColor> productColor = productColorRepository.getAllByProductId(product.getId());

        List<AlumColorStockRequest> alumColorStockRequests = new ArrayList<>();
        for (ProductColor color : productColor) {
            AlumColorStockRequest alumColorsRepository = new AlumColorStockRequest();
            alumColorsRepository.setAlumColorId(color.getAlumColor().getId());
            alumColorsRepository.setStockColor(color.getStockColor());
            alumColorStockRequests.add(alumColorsRepository);
        }
        productResponse.setAlumColorStockRequests(alumColorStockRequests);
        Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
        productResponse.setCategory(category);
        log.info("DATO HECHOS "+productResponse);
        log.info("DATO CAtego "+category.getId());
        return productResponse;

    }

    public List<Product> getListProduct() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequest productRequest){

        Product product = new Product();
        product.setNameProduct(productRequest.getNameProduct());
        product.setDescriptionProduct(productRequest.getDescriptionProduct());
        product.setStockTotal(productRequest.getStockTotal());
        product.setPrice(productRequest.getPrice());
        product.setState(productRequest.getState());

        Category category = categoryRepository.findById(productRequest.getCategoryId()).get();
        product.setCategory(category);
        Product productNew = productRepository.save(product);
        int pst=productNew.getStockTotal();
        //log.info("DATO J "+ j);
        //log.info("DATO ProductNew "+productNew.getStockTotal());
        for(int i=0;i<productRequest.getAlumColorStockRequests().size();i++){
            AlumColors alumColors = alumColorsRepository.findById(productRequest.getAlumColorStockRequests().get(i).getAlumColorId()).get();
            ProductColor productColor = new ProductColor();
            productColor.setProduct(productNew);
            productColor.setAlumColor(alumColors);
            productColor.setStockColor(productRequest.getAlumColorStockRequests().get(i).getStockColor());
            pst = pst + productRequest.getAlumColorStockRequests().get(i).getStockColor();
            productColorRepository.save(productColor);
        }
        //log.info("DATO J2 "+ j);


        for(int j=0;j<productRequest.getAluminumSeriesRequests().size();j++){
            AluminumSeries aluminumSeries = aluminumSeriesRepository.findById(productRequest.getAluminumSeriesRequests().get(j).getAlumSeriesId()).get();
            ProductAlumSeries productAlumSeries = new ProductAlumSeries();
            productAlumSeries.setProduct(productNew);
            productAlumSeries.setAluminumSeries(aluminumSeries);
            productAlumSeriesRepository.save(productAlumSeries);
        }

        for(int k=0;k<productRequest.getProductImagesRequests().size();k++){
            ProductImages productImages = new ProductImages();
            productImages.setImageProduct(productRequest.getProductImagesRequests().get(k).getImageProduct());
            productImages.setProduct(productNew);
            productImagesRepository.save(productImages);
        }

        productNew.setStockTotal(pst);
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
        //product.setImageProduct(productRequest.getImageProduct());
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
        //product.setImageProduct(productRequest.getImageProduct());
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

    public List<ProductColorResponse> getProductsColorById(Integer productColorId){
        List<Object[]> productColorsAll = productColorRepository.getProductsColors(productColorId);
        log.info("DATOS OBTenidosss yaaa productColors: {} ", productColorsAll);
        List<ProductColorResponse> productColors = productColorRepository.getProductsColors(productColorId)
                .stream()
                .map(result -> new ProductColorResponse((Integer) result[0], (String) result[1], (String) result[2], (Integer) result[3]))
                .collect(Collectors.toList());

        return productColors;

    }

    public List<Category> getProductCategory(Integer productId){

        Product product = productRepository.findById(productId).orElse(null);
        List<Category> category = categoryRepository.findAll();
        List<Category> categoryList = new ArrayList<>();
        for(int i=0;i<category.size();i++){
            Category category1 = new Category();
            if(product.getCategory().getId()!=category.get(i).getId()){
                category1.setId(category.get(i).getId());
                category1.setNameCategory(category.get(i).getNameCategory());
                categoryList.add(category1);
            }
        }
        //log.info("DATOS OBTenidosssssssss: {} ",categoryList);
        return categoryList;

    }

    public List<ProductSeriesResponse> getProductsSeriesById(Integer productSeriesId){
        List<Object[]> productsSeries = productAlumSeriesRepository.getProductsSeries(productSeriesId);
        log.info("DATOS OBTenidosss yaaa productColors: {} ", productsSeries);
        List<ProductSeriesResponse> productsSeriesAll = productAlumSeriesRepository.getProductsSeries(productSeriesId)
                .stream()
                .map(result -> new ProductSeriesResponse((Integer) result[0], (String) result[1]))
                .collect(Collectors.toList());

        return productsSeriesAll;

    }

    public List<ProductImages> getProductsImagesById(Integer productId){

        return productImagesRepository.getAllByProductId(productId);
    }


}
