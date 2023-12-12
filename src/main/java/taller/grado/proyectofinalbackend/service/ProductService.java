package taller.grado.proyectofinalbackend.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import taller.grado.proyectofinalbackend.model.*;
import taller.grado.proyectofinalbackend.model.dao.AlumColorStockRequest;
import taller.grado.proyectofinalbackend.model.dao.ProductRequest;
import taller.grado.proyectofinalbackend.model.dto.ProductColorResponse;
import taller.grado.proyectofinalbackend.model.dto.ProductImagesResponse;
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

    public List<ProductResponse> getListProduct() {
        List<Product> products = productRepository.findAllByStatusIsTrue();
        List<ProductResponse> productResponses = new ArrayList<>();

        for (int i=0;i<products.size();i++){
            ProductResponse productResponse = new ProductResponse();

            productResponse.setId(products.get(i).getId());
            productResponse.setNameProduct(products.get(i).getNameProduct());
            productResponse.setDescriptionProduct(products.get(i).getDescriptionProduct());
            productResponse.setStockTotal(products.get(i).getStockTotal());
            productResponse.setPrice(products.get(i).getPrice());
            productResponse.setSizeProduct(products.get(i).getSizeProduct());
            productResponse.setStatus(products.get(i).getStatus());
            productResponse.setCategory(products.get(i).getCategory());

            List<ProductColorResponse> productColors = productColorRepository.getProductsColors(products.get(i).getId())
                    .stream()
                    .map(result -> new ProductColorResponse((Integer) result[0], (String) result[1], (String) result[2], (Integer) result[3]))
                    .collect(Collectors.toList());

            productResponse.setProductColorResponses(productColors);

            List<ProductSeriesResponse> productsSeriesAll = productAlumSeriesRepository.getProductsSeries(products.get(i).getId())
                    .stream()
                    .map(result -> new ProductSeriesResponse((Integer) result[0], (String) result[1]))
                    .collect(Collectors.toList());

            productResponse.setProductSeriesResponses(productsSeriesAll);

//            List<ProductImagesResponse> productImagesResponses = new ArrayList<>();
//            List<ProductImages> productImages = productImagesRepository.getAllByProductId(products.get(i).getId());
//            for (int j=0; j<productImages.size();j++){
//                ProductImagesResponse productImagesResponse = new ProductImagesResponse();
//                productImagesResponse.setId(productImages.get(j).getId());
//                productImagesResponse.setImageProduct(productImages.get(j).getImageProduct());
//                productImagesResponses.add(productImagesResponse);
//            }
//
//            productResponse.setProductImagesResponses(productImagesResponses);
            productResponses.add(productResponse);
        }

        return productResponses;
    }

    public ProductResponse getProductById(Integer productId){

        Product product = productRepository.findById(productId).orElse(null);
        ProductResponse productResponse = new ProductResponse();

        productResponse.setId(productId);
        productResponse.setNameProduct(product.getNameProduct());
        productResponse.setDescriptionProduct(product.getDescriptionProduct());
        productResponse.setStockTotal(product.getStockTotal());
        productResponse.setPrice(product.getPrice());
        productResponse.setSizeProduct(product.getSizeProduct());
        productResponse.setStatus(product.getStatus());

        Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
        productResponse.setCategory(category);

        List<ProductColorResponse> productColors = productColorRepository.getProductsColors(productId)
                .stream()
                .map(result -> new ProductColorResponse((Integer) result[0], (String) result[1], (String) result[2], (Integer) result[3]))
                .collect(Collectors.toList());

        productResponse.setProductColorResponses(productColors);

        List<ProductSeriesResponse> productsSeriesAll = productAlumSeriesRepository.getProductsSeries(productId)
                .stream()
                .map(result -> new ProductSeriesResponse((Integer) result[0], (String) result[1]))
                .collect(Collectors.toList());

        productResponse.setProductSeriesResponses(productsSeriesAll);

        List<ProductImagesResponse> productImagesResponses = new ArrayList<>();
        List<ProductImages> productImages = productImagesRepository.getAllByProductId(productId);
        for (int i=0; i<productImages.size();i++){
            ProductImagesResponse productImagesResponse = new ProductImagesResponse();
            productImagesResponse.setId(productImages.get(i).getId());
            productImagesResponse.setImageProduct(productImages.get(i).getImageProduct());
            productImagesResponses.add(productImagesResponse);
        }

        productResponse.setProductImagesResponses(productImagesResponses);

        return productResponse;

    }

    public List<Product> getListProductOnly() {
        return productRepository.findAllByStatusIsTrue();
    }

    public Product createProduct(ProductRequest productRequest){

        Product product = new Product();
        product.setNameProduct(productRequest.getNameProduct());
        product.setDescriptionProduct(productRequest.getDescriptionProduct());
        //product.setStockTotal(productRequest.getStockTotal());
        product.setStockTotal(productRequest.getStockTotal());
        product.setSizeProduct(productRequest.getSizeProduct());
        product.setPrice(productRequest.getPrice());
        product.setStatus(true);

        Category category = categoryRepository.findById(productRequest.getCategoryId()).get();
        product.setCategory(category);
        Product productNew = productRepository.save(product);
        int pst=productNew.getStockTotal();
        //log.info("DATO J "+ j);
        //log.info("DATO ProductNew "+productNew.getStockTotal());
        for(int i=0;i<productRequest.getAlumColorStockRequests().size();i++){
            AlumColors alumColors = alumColorsRepository.findById(productRequest.getAlumColorStockRequests().get(i).getId()).get();
            ProductColor productColor = new ProductColor();
            productColor.setProduct(productNew);
            productColor.setAlumColor(alumColors);
            productColor.setStockColor(productRequest.getAlumColorStockRequests().get(i).getStockColor());
            pst = pst + productRequest.getAlumColorStockRequests().get(i).getStockColor();
            productColorRepository.save(productColor);
        }
        //log.info("DATO J2 "+ j);


        for(int j=0;j<productRequest.getAluminumSeriesRequests().size();j++){
            AluminumSeries aluminumSeries = aluminumSeriesRepository.findById(productRequest.getAluminumSeriesRequests().get(j).getId()).get();
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
        product.setStatus(productRequest.getStatus());
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
        product.setStatus(productRequest.getStatus());
        //product.setCategory(category);
        //product.setProductColor(productColor);
        Product productNew = productRepository.save(product);
        //log.info("DATOS OBTenidossssssssss",productNew);
        return productNew;
    }

    public List<ProductColorResponse> getProductsColorById(Integer productColorId){
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
//        List<Object[]> productsSeries = productAlumSeriesRepository.getProductsSeries(productSeriesId);
//        log.info("DATOS OBTenidosss yaaa productColors: {} ", productsSeries);
        List<ProductSeriesResponse> productsSeriesAll = productAlumSeriesRepository.getProductsSeries(productSeriesId)
                .stream()
                .map(result -> new ProductSeriesResponse((Integer) result[0], (String) result[1]))
                .collect(Collectors.toList());

        return productsSeriesAll;

    }

    public List<ProductImages> getProductsImagesById(Integer productId){

        return productImagesRepository.getAllByProductId(productId);
    }

    public ProductImages updateProductImage(ProductImages productImages) {
        List<ProductImages> productImagesList = productImagesRepository.getAllByProductId(productImages.getId());
        //ProductImages productImages1 = productRepository.findOneByProduct_Id();
        //System.out.println("imagen CARGADOOOOOOOOOOO   " + productImagesList.get(0).getImageProduct());
        ProductImages productImages1 = productImagesList.get(0);
        //System.out.println("imagen CARGADOOOOOOOOOOO   " + productImages.getImageProduct());
        productImages1.setImageProduct(productImages.getImageProduct());
        //System.out.println("imagen CARGADOOOOOOOOOOO   " + productImages1.getImageProduct());
        //productImages1.setImageProduct(productImagesList.get(0).getImageProduct());

        return productImagesRepository.save(productImages1);
    }

    public Product deleteProductById(Integer productId) {
        Product product = productRepository.findById(productId).orElse(null);
        product.setStatus(false);
        return productRepository.save(product);
    }

    public List<ProductResponse> getListProductStore() {
        List<Product> products = productRepository.findAllByStatusIsTrue();
        List<ProductResponse> productResponses = new ArrayList<>();

        for (int i=0;i<products.size();i++){
            ProductResponse productResponse = new ProductResponse();

            productResponse.setId(products.get(i).getId());
            productResponse.setNameProduct(products.get(i).getNameProduct());
            productResponse.setDescriptionProduct(products.get(i).getDescriptionProduct());
            productResponse.setStockTotal(products.get(i).getStockTotal());
            productResponse.setPrice(products.get(i).getPrice());
            productResponse.setSizeProduct(products.get(i).getSizeProduct());
            productResponse.setStatus(products.get(i).getStatus());
            productResponse.setCategory(products.get(i).getCategory());

            List<ProductColorResponse> productColors = productColorRepository.getProductsColors(products.get(i).getId())
                    .stream()
                    .map(result -> new ProductColorResponse((Integer) result[0], (String) result[1], (String) result[2], (Integer) result[3]))
                    .collect(Collectors.toList());

            productResponse.setProductColorResponses(productColors);

            List<ProductSeriesResponse> productsSeriesAll = productAlumSeriesRepository.getProductsSeries(products.get(i).getId())
                    .stream()
                    .map(result -> new ProductSeriesResponse((Integer) result[0], (String) result[1]))
                    .collect(Collectors.toList());

            productResponse.setProductSeriesResponses(productsSeriesAll);

            List<ProductImagesResponse> productImagesResponses = new ArrayList<>();
            List<ProductImages> productImages = productImagesRepository.getAllByProductId(products.get(i).getId());
            for (int j=0; j<productImages.size();j++){
                ProductImagesResponse productImagesResponse = new ProductImagesResponse();
                productImagesResponse.setId(productImages.get(j).getId());
                productImagesResponse.setImageProduct(productImages.get(j).getImageProduct());
                productImagesResponses.add(productImagesResponse);
            }

            productResponse.setProductImagesResponses(productImagesResponses);
            productResponses.add(productResponse);
        }

        return productResponses;
    }

    public List<AluminumSeries> getAluminumSeriesList() {
        return aluminumSeriesRepository.findAll();
    }

    public List<AlumColors> getAlumColorsList() {
        return alumColorsRepository.findAll();
    }

    public ProductColorResponse updateProductIdColorAdd(ProductColorResponse productColorResponse) {
        Product product = productRepository.findById(productColorResponse.getId()).orElse(null);
        int pst=0;
        boolean cont=true;
        List<ProductColorResponse> productColors = productColorRepository.getProductsAlumColorsById(productColorResponse.getId())
                .stream()
                .map(result -> new ProductColorResponse((Integer) result[0], (String) result[1], (String) result[2], (Integer) result[3]))
                .collect(Collectors.toList());

        if (productColors.size()>0){
            for(int i=0;i<productColors.size();i++){
                AlumColors alumColors = alumColorsRepository.findById(productColors.get(i).getId()).get();
                pst = pst + productColors.get(i).getStockColor();
//                System.out.println("DATOOOOOOOOOOOSSSSSSSS  CONTA COLOR 1  " + pst);
                if(productColorResponse.getColorName().equals(productColors.get(i).getColorName())){
                    ProductColor productColor = productColorRepository.findOneByProduct_IdAndAlumColor_Id(product.getId(),alumColors.getId());
//                    System.out.println("NUMERO ES Product ID "+ productColor.getProduct().getId());
//                    System.out.println("NUMERO ES Alum ID "+ productColor.getAlumColor().getId());
//                    System.out.println("NUMERO ES Stock "+ productColor.getStockColor());
                    int valorStock = productColorResponse.getStockColor() + productColor.getStockColor();
                    pst = pst + productColorResponse.getStockColor();
                    productColor.setStockColor(valorStock);
//                    System.out.println("DATOOOOOOOOOOOSSSSSSSS  CONTA COLOR 2  " + pst);
                    productColorRepository.save(productColor);
                    cont = false;
                }
            }
        }
        if(cont){
            AlumColors alumColorOne = alumColorsRepository.findOneByColorName(productColorResponse.getColorName()).get();
            ProductColor productColor = new ProductColor();
            productColor.setProduct(product);
            productColor.setAlumColor(alumColorOne);
            productColor.setStockColor(productColorResponse.getStockColor());
            pst = pst + productColorResponse.getStockColor();
//            System.out.println("DATOOOOOOOOOOOSSSSSSSS  CONTA TRUE" + pst);
            productColorRepository.save(productColor);
        }
        product.setStockTotal(pst);
        productRepository.save(product);
        return productColorResponse;
    }


}
