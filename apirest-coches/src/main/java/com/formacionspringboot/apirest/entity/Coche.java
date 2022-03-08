package com.formacionspringboot.apirest.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="coches")
public class Coche {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	private String color;
	
	private String matricula;
	
	private Long cilindrada;
	
	private Long velocidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="marca_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Marca marca;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="modelo_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Modelo modelo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Marca getMarca() {
		return marca;
	}


	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public Modelo getModelo() {
		return modelo;
	}


	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Long getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(Long cilindrada) {
		this.cilindrada = cilindrada;
	}

	public Long getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(Long velocidad) {
		this.velocidad = velocidad;
	}
	
	

}
