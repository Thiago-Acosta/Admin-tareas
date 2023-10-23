<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formateo fechas (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Formulario form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- para errores de renderizado en rutas PUT -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mostrar</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>

	<div class="text-end">
		<a href="/logout">Cerrar sesion</a>
	</div>
		<div class="text-end">
		<a href="/dashboard">Volver a inicio</a>
	</div>
	
	<h1 class = "text-center"><ins>Task: <c:out value="${tareas.task }"></c:out></ins></h1>
	<h4 class = "ms-3"><ins>Creador:</ins>  <c:out value="${tareas.creador.name }"></c:out></h4>
	<h4 class = "ms-3"><ins>asistente: :</ins> <c:out value="${tareas.asistente.name }"></c:out></h4>
	<!-- Mostrar el tipo de medida -->
	<c:if test="${tareas.priority == 1}">
		<h4 class = "ms-3"><ins>Prioridad:</ins> Low</h4>
	</c:if>
	<c:if test="${tareas.priority == 2}">
		<h4 class = "ms-3"><ins>Prioridad:</ins> Medium</h4>
	</c:if>
	<c:if test="${tareas.priority == 3}">
		<h4 class = "ms-3"><ins>Prioridad:</ins> High</h4>
	</c:if>
	
	<c:if test="${tareas.creador.id == usuario.id}">	
		<a href = "/tarea/${tareas.id}/edit" role="button" class="btn btn-outline-primary ms-3">Edit</a>
	
		<form:form action="/tarea/${tareas.id}/delete" method="post" class = "mt-2">
			<input type="hidden" name="_method" value="delete" />
			<input type="submit" value="Delate" class = "btn btn-outline-danger ms-3" />
		</form:form>
	</c:if>
	
		<c:if test="${tareas.asistente.id == usuario.id}">	
	
		<form:form action="/tarea/${tareas.id}/delete" method="post" class = "mt-2">
			<input type="hidden" name="_method" value="delete" />
			<div class="text-end">
				<input type="submit" value="Completado" class = "btn btn-outline-primary m-3" />
			</div>
		</form:form>
	</c:if>
	
</body>
</html>