package com.thiago.ExamenThiago.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.thiago.ExamenThiago.models.tareaModel;
import com.thiago.ExamenThiago.repositories.tareaRepo;

@Service
public class mainService {

	private final tareaRepo tareaRepo;
	public mainService(tareaRepo tRepo) {
		this.tareaRepo = tRepo;
	}
	
	//Crear tarea
	public tareaModel crearTarea(tareaModel tarea) {
		
		return tareaRepo.save(tarea);
	}
	
	//lista de tareas
	public List<tareaModel> todasTareas(){
		return tareaRepo.findAll();
	}
	
	//tarea por id
	public tareaModel mostrarTareaPorId(Long id) {
		return tareaRepo.findById(id).orElse(null);
	}
	
	// Editar tarea
	public tareaModel actualizarTarea(tareaModel tareaModel) {
		return tareaRepo.save(tareaModel);
	}
	//Eliminar tarea
	public void borrarTarea(Long id) {
		tareaRepo.deleteById(id);
	}
	
	//Tareas desc
	public List<tareaModel> obtenerTareasDesc() {
	    return tareaRepo.findIdeasOrderByTareasDesc();
	}
	
	//Tareas asc
	public List<tareaModel> obtenerTareasAsc() {
	    return tareaRepo.findIdeasOrderByTareasAsc();
	}
}
