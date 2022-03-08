package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.CocheDao;
import com.formacionspringboot.apirest.dao.ModeloDao;
import com.formacionspringboot.apirest.entity.Coche;
import com.formacionspringboot.apirest.entity.Marca;
import com.formacionspringboot.apirest.entity.Modelo;


@Service
public class ModeloServiceImpl implements ModeloService{
	
	@Autowired
	private ModeloDao modeloDao;

	@Override
	@Transactional(readOnly=true)
	public List<Modelo> findAll() {
		return (List<Modelo>) modeloDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Modelo findById(Long id) {
		return modeloDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Modelo save(Modelo modelo) {
		return modeloDao.save(modelo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		modeloDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Modelo> findAllModelos() {
		
		return modeloDao.findAllModelos();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Modelo> findAllModelos() {
		
		return modeloDao.findAllModels();
	}

}
