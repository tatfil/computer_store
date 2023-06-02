package org.example.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreationDto {

    @NotEmpty(message = "Product name may not be empty")
    @Size(max = 50)
    private String name;

    @NotEmpty(message = "Brand may not be empty")
    @Size(max = 50)
    private String brand;

    @NotEmpty(message = "Price may not be empty")
    @Size(max = 50)
    private Integer price;

    @NotEmpty(message = "Category may not be empty")
    @Size(max = 50)
    private String productCategoryName;

    @Size(max = 9)
    private Map<String, List<String>> productFields;
}
