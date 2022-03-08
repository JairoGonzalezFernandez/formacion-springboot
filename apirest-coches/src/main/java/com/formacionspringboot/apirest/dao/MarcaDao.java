package com.formacionspringboot.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.entity.Marca;



@Repository
public interface MarcaDao extends CrudRepository<Marca, Long>{

}
