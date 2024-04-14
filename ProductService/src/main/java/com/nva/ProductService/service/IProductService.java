package com.nva.ProductService.service;

import com.nva.ProductService.dto.request.ProductDTORequest;
import com.nva.ProductService.dto.response.ProductDTOResponse;

public interface IProductService {
    ProductDTOResponse createProduct(ProductDTORequest productDTORequest);
}
