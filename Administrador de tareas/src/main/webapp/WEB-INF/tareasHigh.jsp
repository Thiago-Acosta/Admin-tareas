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
<title>Orden High</title>
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
	
	<h1 class = "text-center">Welcome, <c:out value="${usuario.name }"></c:out></h1>
	
	<div class="text-end me-3">
		<a href = "/tareas/high" class = "d-inline me-3">Priority high-low</a>
		<a href = "/tareas/low"  class = "d-inline ">Priority low-high</a>
	</div>
	
	
	<table class="table table-bordered border-dark mt-2">
		<thead class ="text-center ">
			<tr >
				<th class = "table-light">Task</th>
				<th class = "table-light">Creator</th>
				<th class = "table-light">Assignee</th>
				<th class = "table-light">Priority</th>
			</tr>
		</thead>
		<tbody class ="text-center ">	
			<c:forEach items="${tareasHight}" var="tareas">
				<tr>
					<td>
						<a href = "/tarea/${tareas.id}"><c:out value="${tareas.task}"></c:out></a>
					</td>
					<td><c:out value="${tareas.creador.name }"></c:out></td>
					<td><c:out value="${tareas.asistente.name }"></c:out></td>
					
					<!-- Mostrar el tipo de medida -->
					<c:if test="${tareas.priority == 1}">
						<td>Low</td>
					</c:if>	
					<c:if test="${tareas.priority == 2}">
						<td>Medium</td>
					</c:if>	
					<c:if test="${tareas.priority == 3}">
						<td>High</td>
					</c:if>	
				</tr>
			</c:forEach>	
		</tbody>
	</table>
	
	<div class="text-end m-3">
		<a href = "/tarea/new" class="btn btn-primary ">Create Task </a>
	</div>
	
	
</body>
</html>