package com.formacionspringboot.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.entity.Coche;



@Repository
public interface CocheDao extends CrudRepository<Coche, Long>{

}
