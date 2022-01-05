package com.gabriel.gcscollegeAPI.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private int quantityEnrolments;
	
	
	public void increaseQuantityEnrolments() {
		this.quantityEnrolments++;
	}
	
	public void decreaseQuantityEnrolments() {
		this.quantityEnrolments--;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantityEnrolments() {
		return quantityEnrolments;
	}

	public void setQuantityEnrolments(int quantityEnrolments) {
		this.quantityEnrolments = quantityEnrolments;
	}


}
