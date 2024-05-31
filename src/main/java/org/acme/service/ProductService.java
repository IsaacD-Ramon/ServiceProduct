package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ProductDTO;
import org.acme.entity.ProductEntity;
import org.acme.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<ProductDTO> getAllProduct(){

        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll().stream().forEach(item->{
            products.add(mapProductEntityToDto(item));
        });

        return  products;
    }

    public ProductDTO findProductById(Long id){
        return mapProductEntityToDto(productRepository.findById(id));
    }

    public void createNewProduct(ProductDTO productDTO){
        productRepository.persist(mapProductDtoToEntity(productDTO));
    }

    public void changeProduct(Long id, ProductDTO obj){

        ProductEntity product = productRepository.findById(id);

        product.setName(obj.getName());
        product.setDescription(obj.getDescription());
        product.setCategory(obj.getCategory());
        product.setModel(obj.getModel());
        product.setPrice(obj.getPrice());

        productRepository.persist(product);

    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


    private ProductEntity mapProductDtoToEntity(ProductDTO obj){

        ProductEntity product = new ProductEntity();

        product.setName(obj.getName());
        product.setDescription(obj.getDescription());
        product.setCategory(obj.getCategory());
        product.setModel(obj.getModel());
        product.setPrice(obj.getPrice());

        return product;
    }

    private ProductDTO mapProductEntityToDto(ProductEntity obj){

        ProductDTO product = new ProductDTO();

        product.setName(obj.getName());
        product.setDescription(obj.getDescription());
        product.setCategory(obj.getCategory());
        product.setModel(obj.getModel());
        product.setPrice(obj.getPrice());

        return product;
    }
}
