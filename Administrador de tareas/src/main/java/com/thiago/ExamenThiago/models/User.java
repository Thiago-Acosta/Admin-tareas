package com.thiago.ExamenThiago.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	//Datos de la tabla
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message=" Por favor ingresa un Nombre")
	private String name;
	
	@NotBlank(message=" Por favor ingresa un correo electronico")
	@Email(message="El correo ingresado no es correcto")
	private String email;
	
	//@NotBlank(message="Por favor, ingresa el password")
	@Size(min=8, max=64, message= "Por favor ingresa el password")
	private String password;
	
	@Transient
	//@NotBlank(message="Por favor confirma la contrasenia")
	@Size(min=8, message= "Por favor confirma el password")
	private String passwordConfirmation;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	@OneToMany(mappedBy = "creador", fetch = FetchType.LAZY)
	private List<tareaModel> tareas;
	
	@OneToMany(mappedBy = "asistente", fetch = FetchType.LAZY)
	private List<tareaModel> tareasAsis;
	
	//constructor
	public User() {
	}
	


	public List<tareaModel> getTareasAsis() {
		return tareasAsis;
	}



	public void setTareasAsis(List<tareaModel> tareasAsis) {
		this.tareasAsis = tareasAsis;
	}



	public List<tareaModel> getTareas() {
		return tareas;
	}



	public void setTareas(List<tareaModel> tareas) {
		this.tareas = tareas;
	}



	//Set y get 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
