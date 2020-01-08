package com.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Category {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	@JsonIgnore
	private int categoryId;
	@Column(name = "category_name")
	private String categoryName;
	@Column(name = "subcategory_name")
	private String subcategoryName;
	private String gender;
}
