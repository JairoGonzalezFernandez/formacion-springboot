package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.CocheDao;
import com.formacionspringboot.apirest.entity.Cliente;
import com.formacionspringboot.apirest.entity.Coche;
import com.formacionspringboot.apirest.entity.Marca;
import com.formacionspringboot.apirest.entity.Modelo;
import com.formacionspringboot.apirest.entity.Region;

@Service
public class CocheServiceImpl implements CocheService{
	
	@Autowired
	private CocheDao cocheDao;

	@Override
	@Transactional(readOnly=true)
	public List<Coche> findAll() {
		return (List<Coche>) cocheDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Coche findById(Long id) {
		return cocheDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Coche save(Coche coche) {
		return cocheDao.save(coche);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cocheDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Marca> findAllMarcas() {
		
		return cocheDao.findAllMarcas();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Modelo> findAllModelos() {
		
		return cocheDao.findAllModels();
	}

}
