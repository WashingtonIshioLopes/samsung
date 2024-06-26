package com.samsung.springboot.models;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;

// Hateoas
//import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "tb_products")
public class ProductModel implements Serializable{

	// Hateoas
	//public class ProductModel extends RepresentationModel<com.example.springboot.models.ProductModel> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long  idProduct;
	private String description;
	private BigDecimal price;
	private Long IdUnit;
	private Long IdCategory;
	private BigDecimal weight;

	public Long getIdCategory() {
		return IdCategory;
	}

	public void setIdCategory(Long idCategory) {
		IdCategory = idCategory;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
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

	public Long getIdUnit() {
		return IdUnit;
	}

	public void setIdUnit(Long idUnit) {
		IdUnit = idUnit;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

}
