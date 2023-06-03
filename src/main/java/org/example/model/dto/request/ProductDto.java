package org.example.model.dto.request;

import lombok.*;
import org.example.model.entity.Property;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private long id;

    @NotEmpty(message = "Product name may not be empty")
    @Size(max = 50)
    private String name;

    @NotEmpty(message = "Brand may not be empty")
    @Size(max = 50)
    private String brand;

    @NotNull(message = "Price may not be null")
    private Integer price;

    @NotEmpty(message = "Category may not be empty")
    @Size(max = 50)
    private String productCategoryName;

    @Size(max = 9)
    @OneToMany(mappedBy="property")
    private Set<Property> properties;
}
