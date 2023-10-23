package com.thiago.ExamenThiago.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.thiago.ExamenThiago.models.User;
import com.thiago.ExamenThiago.models.tareaModel;
import com.thiago.ExamenThiago.services.UserService;
import com.thiago.ExamenThiago.services.mainService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AdminTareasController {
	
	private final UserService userServ;
	private final mainService mainService;
	public AdminTareasController (UserService uSer,mainService mSer) {
		this.userServ = uSer;
		this.mainService = mSer;
	}
	
	//Pagina Principal
	@GetMapping("/dashboard")
	public String bienvenido( @ModelAttribute("tarea") tareaModel tareaModel, BindingResult resultado,
			HttpSession sesion, Model viewModel) {
		
		// validar si la sesion del usuario esta activa
		Long userId =  (Long) sesion.getAttribute("userID");
		if(userId == null ) {
			return "redirect:/";
		}
		User usuario = userServ.encontrarUserPorId(userId);
		viewModel.addAttribute("usuario", usuario);
		viewModel.addAttribute("usuarios", userServ.todosUsuarios());
		viewModel.addAttribute("tareas", mainService.todasTareas());
		
		return "dashboard.jsp";	
	}
	
	
	
	//guardar y mostrar idea
	@GetMapping("/tarea/new")
	public String formTarea(@ModelAttribute("tarea") tareaModel tareaModel, BindingResult resultado,
			HttpSession sesion, Model viewModel) {
		
		// validar si la sesion del usuario esta activa
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		User usuario = userServ.encontrarUserPorId(userId);
		viewModel.addAttribute("usuario", usuario);
		viewModel.addAttribute("usuarios", userServ.todosUsuarios());
		
		return "crear.jsp"; 
	}
	
	//crear idea
	@PostMapping("/tarea/new")
	public String crearIdea(@Valid @ModelAttribute("tarea") tareaModel tareaModel, BindingResult resultado,
			HttpSession sesion, Model viewModel) {
		
		// validar si la sesion del usuario esta activa
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		if (resultado.hasErrors()) {
			User usuario = userServ.encontrarUserPorId(userId);
			viewModel.addAttribute("usuario", usuario);
			viewModel.addAttribute("usuarios", userServ.todosUsuarios());
			return "crear.jsp";
		}
		mainService.crearTarea(tareaModel);
		return "redirect:/dashboard";
	}
	
	//mostrar tarea
	@GetMapping("/tarea/{idTarea}")
	public String mostrarTarea(Model viewModel, @PathVariable("idTarea") Long idTarea, HttpSession sesion) {
		
		// validar si la sesion del usuario esta activa
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		User usuario = userServ.encontrarUserPorId(userId);
		viewModel.addAttribute("usuario", usuario);
		viewModel.addAttribute("tareas", mainService.mostrarTareaPorId(idTarea));
		return "Mostrar.jsp";
	}
	
	//eliminar song
	@DeleteMapping("/tarea/{idTarea}/delete")
	public String borrarSong(@PathVariable("idTarea") Long idTarea) {
		mainService.borrarTarea(idTarea);
		return "redirect:/dashboard";
	}

	//Mostrar edicion de tarea
	@GetMapping("/tarea/{idTarea}/edit")
	public String formEdicionTarea(@PathVariable("idTarea") Long idTarea,
			@ModelAttribute("tarea") tareaModel tareaModel, HttpSession sesion, Model viewModel) {
		
		// validar si la sesion del usuario esta activa
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		
		tareaModel unasong = mainService.mostrarTareaPorId(idTarea);
		if (unasong == null) {
			return "redirect:/dashboard";
		}
		
		User usuario = userServ.encontrarUserPorId(userId);
	    // Verificar si el usuario actual es el autor de la tarea
	    boolean esAutor = unasong.getCreador().getId().equals(usuario.getId());
	    if (esAutor) {
	    	
			viewModel.addAttribute("usuario", usuario);
			viewModel.addAttribute("usuarios", userServ.todosUsuarios());
			viewModel.addAttribute("tarea", unasong);
	        return "Edit.jsp";
	    } else {
	        return "redirect:/dashboard";
	    }
	}
	
	@PutMapping("/tarea/{id}/edit")
	public String editarSong(@Valid @ModelAttribute("tarea") tareaModel tareaModel, BindingResult resultado,
			@PathVariable("id") Long idTarea ,HttpSession sesion, Model viewModel) {
		
		// validar si la sesion del usuario esta activa
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		User usuario = userServ.encontrarUserPorId(userId);
		if (resultado.hasErrors()) {
			viewModel.addAttribute("usuario", usuario);
			viewModel.addAttribute("usuarios", userServ.todosUsuarios());
			return "Edit.jsp";
		}
		mainService.actualizarTarea(tareaModel);
		return "redirect:/dashboard";
	}
	
	//Orden de tareas
		@GetMapping("/tareas/high")
		public String listarTareasHight(Model viewModel,  HttpSession sesion) {
		    List<tareaModel> ideasOrdenadasHight = mainService.obtenerTareasDesc();
		    
			// validar si la sesion del usuario esta activa
			Long userId =  (Long) sesion.getAttribute("userID");
			if(userId == null ) {
				return "redirect:/";
			}
			
			User usuario = userServ.encontrarUserPorId(userId);
			viewModel.addAttribute("usuario", usuario);
			viewModel.addAttribute("usuarios", userServ.todosUsuarios());
		    viewModel.addAttribute("tareasHight", ideasOrdenadasHight);
		    return "tareasHigh.jsp"; 
		}
		
		//Orden de tareas
		@GetMapping("/tareas/low")
		public String listarTareasLow(Model viewModel,  HttpSession sesion) {
		    List<tareaModel> ideasOrdenadaLow = mainService.obtenerTareasAsc();
		    
			// validar si la sesion del usuario esta activa
			Long userId =  (Long) sesion.getAttribute("userID");
			if(userId == null ) {
				return "redirect:/";
			}
			
			User usuario = userServ.encontrarUserPorId(userId);
			viewModel.addAttribute("usuario", usuario);
			viewModel.addAttribute("usuarios", userServ.todosUsuarios());
		    viewModel.addAttribute("tareasLow", ideasOrdenadaLow);
		    return "tareasLow.jsp"; 
		}

}
