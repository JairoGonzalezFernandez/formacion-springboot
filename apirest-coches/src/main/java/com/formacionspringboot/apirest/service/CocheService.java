package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.entity.Coche;
import com.formacionspringboot.apirest.entity.Marca;
import com.formacionspringboot.apirest.entity.Modelo;



public interface CocheService {
	
public List<Coche> findAll();
	
	public Coche findById(Long id);
	
	public Coche save(Coche coche);
	
	public void delete(Long id);
	
	
	public List<Marca> findAllMarcas();
	
	public List<Modelo> findAllModelos();

}
