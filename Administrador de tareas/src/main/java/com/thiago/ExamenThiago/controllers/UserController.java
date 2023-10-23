package com.thiago.ExamenThiago.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.thiago.ExamenThiago.models.LogReg;
import com.thiago.ExamenThiago.models.User;
import com.thiago.ExamenThiago.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private final UserService userServ;
	public UserController(UserService uSer) {
		this.userServ = uSer;
	}
	
	@GetMapping(value = "/")
	public String index() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String raiz(Model viewModel) {
		viewModel.addAttribute("login", new LogReg());
		return "/registro/loginreg.jsp";
	}
	
	@GetMapping("/registro")
	public String registro(Model viewModel) {
		viewModel.addAttribute("user", new User());
		return "/registro/registro.jsp";
	}
	
	@PostMapping("/registro")
	public String registro(@Valid @ModelAttribute("user") User usuario,
			BindingResult resultado, Model viewModel ) {
		if(resultado.hasErrors()) {
			viewModel.addAttribute("login", new LogReg());
			return "/registro/registro.jsp";
		}
		User usuarioRegistrado = userServ.registroUsuario(usuario, resultado);
		viewModel.addAttribute("login", new LogReg());
		if(usuarioRegistrado != null) {
			viewModel.addAttribute("registro", "Gracias por registrarte, ahora login por favor");
			return "redirect:/login";
		}
		return "/registro/registro.jsp";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("login") LogReg loginuser,
			BindingResult resultado, Model viewModel, HttpSession sesion) {
		if (resultado.hasErrors()) {
			viewModel.addAttribute("user", new User());
			return "/registro/loginreg.jsp";
		}
		
		if(userServ.authenthicateUser(
				loginuser.getEmail(), 
				loginuser.getPassword(), 
				resultado )) {
			User usuarioLog = userServ.encontrarPorEmail(loginuser.getEmail());
			sesion.setAttribute("userID",usuarioLog.getId());
			return "redirect:/dashboard";
		}else {
			viewModel.addAttribute("errorLog", "Por favor intenta de nuevo");
			viewModel.addAttribute("user", new User());
			return "/registro/loginreg.jsp";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.setAttribute("userID", null);
		return "redirect:/";
	}

}
