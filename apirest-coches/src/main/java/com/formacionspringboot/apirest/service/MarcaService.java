package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.entity.Marca;




public interface MarcaService {
	
public List<Marca> findAll();
	
	public Marca findByNombre(String nombre);
	
	public Marca save(Marca marca);
	
	public void delete(Long id);

	public Marca findById(Long id);

}
