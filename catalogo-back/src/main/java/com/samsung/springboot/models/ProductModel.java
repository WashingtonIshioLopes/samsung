package com.samsung.springboot.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

// Hateoas
//import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "tb_products")
public class ProductModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long  id;

	private String description;

	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "id_unit")
	private UnitModel unit;

	@ManyToOne
	@JoinColumn(name = "id_category")
	private CategoryModel category;

	private BigDecimal weight;

    @JsonIgnoreProperties({"product"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product")
    private List<ProductImageModel> images;


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UnitModel getUnit() {
        return unit;
    }

    public void setUnit(UnitModel unit) {
        this.unit = unit;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public List<ProductImageModel> getImages() {
        return images;
    }

    public void setImages(List<ProductImageModel> images) {
        this.images = images;
    }
}
