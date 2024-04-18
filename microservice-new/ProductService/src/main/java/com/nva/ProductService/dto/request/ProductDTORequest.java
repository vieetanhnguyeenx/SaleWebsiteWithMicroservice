package com.nva.ProductService.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTORequest {
    @NotBlank(message = "Product Name is required")
    String name;

    String description;

    @NotNull(message = "Product price is required")
    @Min(value = 0, message = "Product price must be greater or equal to 0")
    BigDecimal price;
}
