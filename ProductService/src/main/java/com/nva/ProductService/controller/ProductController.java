package com.nva.ProductService.controller;

import com.nva.ProductService.dto.request.ProductDTORequest;
import com.nva.ProductService.dto.response.ProductDTOResponse;
import com.nva.ProductService.exception.ApiRequestException;
import com.nva.ProductService.service.IProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    IProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTOResponse createProduct(
            @Valid @RequestBody ProductDTORequest request,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            throw ApiRequestException.badRequest(errorMsg);
        }
        return productService.createProduct(request);
    }
}
