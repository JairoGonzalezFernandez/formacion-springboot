package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.entity.Modelo;

public interface ModeloService {
	
public List<Modelo> findAll();
	
	public Modelo findByNombre(String nombre);
	
	public Modelo save(Modelo modelo);
	
	public void delete(Long id);

	public Modelo findById(Long id);

}
