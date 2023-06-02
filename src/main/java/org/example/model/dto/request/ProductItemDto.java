package org.example.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemDto {

    @Valid
    @Schema(description = "Product")
    @NotNull(message = "Product items may not be empty")
    private ProductCreationDto product;

    @Schema(description = "Product item quantity", example = "20")
    @NotNull(message = "Product quantity may not be null")
    @Range(min = 1, max = 1000000, message = "Product quantity can be min 1 and max 1000000")
    private Long quantity;
}
