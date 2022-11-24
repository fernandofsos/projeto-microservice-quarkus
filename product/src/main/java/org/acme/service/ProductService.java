package org.acme.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.acme.dto.ProductDto;
import org.acme.entity.ProductEntity;
import org.acme.repository.ProductRepository;
import org.apache.commons.beanutils.BeanUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<ProductDto> getAllProduct(){
        List<ProductDto> lstProductDto = new ArrayList<>();
        productRepository.findAll().stream().forEach(item -> {
            ProductDto productDto = new ProductDto();
            try {
                BeanUtils.copyProperties(productDto,item);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            lstProductDto.add(productDto);
        });
        return lstProductDto;
    }

    public void createNewProduct(ProductDto productDto){

        productRepository.persist(mapProductDtoToEntity(productDto));
    }

    public void changeProduct(Long id, ProductDto productDto){
        ProductEntity product = productRepository.findById(id);

        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setModel(productDto.getModel());
        product.setPrice(productDto.getPrice());

        productRepository.persist(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


    private ProductDto mapProductEntityToDto(ProductEntity productEntity){

        ProductDto productDto = new ProductDto();

        productDto.setName(productEntity.getName());
        productDto.setCategory(productEntity.getCategory());
        productDto.setDescription(productEntity.getDescription());
        productDto.setModel(productEntity.getModel());
        productDto.setPrice(productEntity.getPrice());

        return productDto;
    }

    private ProductEntity mapProductDtoToEntity(ProductDto productDto){

        ProductEntity productEntity = new ProductEntity();

        productEntity.setName(productDto.getName());
        productEntity.setCategory(productDto.getCategory());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setModel(productDto.getModel());
        productEntity.setPrice(productDto.getPrice());

        return productEntity;
    }
}
