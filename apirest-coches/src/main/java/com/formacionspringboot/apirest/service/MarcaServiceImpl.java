package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.MarcaDao;
import com.formacionspringboot.apirest.entity.Marca;


@Service
public class MarcaServiceImpl implements MarcaService{
	
	@Autowired
	private MarcaDao marcaDao;

	@Override
	@Transactional(readOnly=true)
	public List<Marca> findAll() {
			return (List<Marca>) marcaDao.findAll();
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Marca findById(Long id) {
		return marcaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Marca save(Marca marca) {
		return marcaDao.save(marca);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		marcaDao.deleteById(id);
		
	}


	@Override
	@Transactional(readOnly=true)
	public Marca findByNombre(String nombre) {
		Marca marcaExiste = marcaDao.findByAll(id);	
		return marcaExiste;		
	}

}
