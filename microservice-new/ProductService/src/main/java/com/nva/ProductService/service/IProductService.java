package com.nva.ProductService.service;

import com.nva.ProductService.dto.request.ProductDTORequest;
import com.nva.ProductService.dto.response.ProductDTOResponse;

import java.util.List;

public interface IProductService {
    ProductDTOResponse createProduct(ProductDTORequest productDTORequest);

    List<ProductDTOResponse> getAllProduct();
}
