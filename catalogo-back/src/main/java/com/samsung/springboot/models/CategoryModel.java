package com.samsung.springboot.models;

import jakarta.persistence.*;

import java.io.Serializable;

// Hateoas
//import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "tb_productcategorys")
public class CategoryModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProductCategory;
	private String description;
	private String code;

	public Long getIdProductCategory() {
		return idProductCategory;
	}

	public void setIdProductCategory(Long idProductCategory) {
		this.idProductCategory = idProductCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
