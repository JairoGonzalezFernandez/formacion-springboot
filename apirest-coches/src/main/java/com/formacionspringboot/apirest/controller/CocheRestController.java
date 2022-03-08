package com.formacionspringboot.apirest.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.formacionspringboot.apirest.entity.Coche;
import com.formacionspringboot.apirest.entity.Marca;
import com.formacionspringboot.apirest.entity.Modelo;
import com.formacionspringboot.apirest.service.CocheService;



public class CocheRestController {
	
	@Autowired
	private CocheService servicio;
	
	@GetMapping({"/coches","/todos"})
	public List<Coche> index(){
		return servicio.findAll();
		
	}
	/*
	@GetMapping("/clientes/{id}")
	public Cliente findClienteById(@PathVariable Long id) {
		return servicio.findById(id);
	}*/
	
	@GetMapping("/coches/{id}")
	public ResponseEntity<?> findCocheById(@PathVariable Long id){
		Coche coche = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			coche = servicio.findById(id);
			
		}catch (DataAccessException e) {
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		if(coche == null) {
			
			response.put("mensaje", "El coche ID:" +id.toString()+("no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<Coche>(coche,HttpStatus.OK);
	}
	
	
	/*@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente saveCliente(@RequestBody Cliente cliente) {
		return servicio.save(cliente);
	}*/
	
	public ResponseEntity<?> saveCoche(@RequestBody Coche coche){
		Coche cocheNew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			cocheNew = servicio.save(coche);
			
		}catch (DataAccessException e){
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El coche ha sido creado con exito!");
		response.put("coche", cocheNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	/*@PutMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente updateCliente(@RequestBody Cliente cliente,@PathVariable Long id) {
		Cliente clienteUpdate = servicio.findById(id);
		
		clienteUpdate.setNombre(cliente.getNombre());
		clienteUpdate.setApellido(cliente.getApellido());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setTelefono(cliente.getTelefono());
		clienteUpdate.setCreatedAt(cliente.getCreatedAt());
		
		return servicio.save(clienteUpdate);
	}*/
	
	
	@PutMapping("/coche/{id}")
	public ResponseEntity<?> updateCoche(@RequestBody Coche coche, @PathVariable Long id){
		Coche cocheActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(cocheActual == null) {
			response.put("mensaje", "Error: no se puedo editar, el coche con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		
		try {
			
			cocheActual.setColor(coche.getColor());
			cocheActual.setMatricula(coche.getMatricula());
			cocheActual.setCilindrada(coche.getCilindrada());
			cocheActual.setVelocidad(coche.getVelocidad());
			cocheActual.setMarca(coche.getMarca());
			cocheActual.setModelo(coche.getModelo());
			
			servicio.save(cocheActual);
			
		}catch (DataAccessException e){
			
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje","El coche ha sido actualizao con exito!");
		response.put("coche", cocheActual);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
		
	}
	
	
	/*@DeleteMapping("/cliente/{id}")
	public void deleteCliente(@PathVariable Long id) {
		servicio.delete(id);
		
	}*/
	
	
	@DeleteMapping("/coche/{id}")
	public ResponseEntity<?> deleteCoche(@PathVariable Long id) {
		
		Coche cocheActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(cocheActual == null) {
			response.put("mensaje", "Error: no se puedo editar, el coche con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		
		try {
		
		servicio.delete(id);
		
		
		
		}catch (DataAccessException e) {
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		response.put("mensaje","El coche ha sido eliminado con exito!");
		response.put("coche", cocheActual);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}

	
	
	@PostMapping("coche/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		
		Map<String, Object> response = new HashMap<>();
		
		Coche coche = servicio.findById(id);
		
		if(!archivo.isEmpty()) {
			//String nombreArchivo = archivo.getOriginalFilename();
			String nombreArchivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ", " ");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			
		
		
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
			
			
		}catch (IOException e){
			
			response.put("mensaje", "Error al subir la imagen");
			response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
		
		
		
		}
		
		
		servicio.save(coche);
		
		response.put("cliente", coche);
		
		
	
	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	
	}
	
	
	
		
	
	
	@GetMapping("/coche/modelos")
	public List<Modelo> listarModelos(){
		return servicio.findAllModelos();
	}
	
	@GetMapping("/coche/marcas")
	public List<Marca> listarMarcas(){
		return servicio.findAllMarcas();
	}

}
