package com.nva.OrderService.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryDTOResponse {
    String skuCode;
    boolean isInStock;
}
