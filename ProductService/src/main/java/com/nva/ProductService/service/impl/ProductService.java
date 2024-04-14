package com.nva.ProductService.service.impl;

import com.nva.ProductService.dto.request.ProductDTORequest;
import com.nva.ProductService.dto.response.ProductDTOResponse;
import com.nva.ProductService.entity.Product;
import com.nva.ProductService.repository.ProductRepository;
import com.nva.ProductService.service.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService implements IProductService {

    ModelMapper mapper;
    ProductRepository productRepository;

    @Override
    public ProductDTOResponse createProduct(ProductDTORequest productDTORequest) {
        Product product = mapper.map(productDTORequest, Product.class);
        product = productRepository.save(product);
        log.info("Product is saved " + product.getId());
        return mapper.map(product, ProductDTOResponse.class);
    }

    @Override
    public List<ProductDTOResponse> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(product -> mapper.map(product, ProductDTOResponse.class))
                .collect(Collectors.toList());
    }
}
