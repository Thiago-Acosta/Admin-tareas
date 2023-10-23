package com.thiago.ExamenThiago.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.thiago.ExamenThiago.models.tareaModel;

public interface tareaRepo extends CrudRepository<tareaModel, Long>  {
	
	List <tareaModel> findAll();
	
	//CONSULTA PARA OBTENER IDEAS ORDENADAS POR LA CANTIDAD DE TAREAS DE FORMA DESCENDENTE
    @Query(value = "SELECT * FROM tareas" + " ORDER BY tareas.priority DESC", nativeQuery = true)
    List<tareaModel> findIdeasOrderByTareasDesc();
    
	//CONSULTA PARA OBTENER IDEAS ORDENADAS POR LA CANTIDAD DE TAREAS DE FORMA DESCENDENTE
    @Query(value = "SELECT * FROM tareas" + " ORDER BY tareas.priority ASC", nativeQuery = true)
    List<tareaModel> findIdeasOrderByTareasAsc();

}
