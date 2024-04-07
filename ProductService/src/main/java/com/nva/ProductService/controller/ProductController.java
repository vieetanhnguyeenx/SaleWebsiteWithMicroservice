package com.nva.ProductService.controller;

import com.nva.ProductService.dto.ProductDTORequest;
import com.nva.ProductService.dto.ProductDTOResponse;
import com.nva.ProductService.exception.ApiRequestException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

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
        return null;
    }
}
