package com.samsung.springboot.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

// Hateoas
//import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "tb_productimages")
public class ProductImageModel implements Serializable{

	// Hateoas
	//public class ProductModel extends RepresentationModel<com.example.springboot.models.ProductModel> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long  idProductImage;
	private Long IdProduct;
	private String image;

	public Long getIdProductImage() {
		return idProductImage;
	}

	public void setIdProductImage(Long idProductImage) {
		this.idProductImage = idProductImage;
	}

	public Long getIdProduct() {
		return IdProduct;
	}

	public void setIdProduct(Long idProduct) {
		IdProduct = idProduct;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
