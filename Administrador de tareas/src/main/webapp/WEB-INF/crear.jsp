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
<title>Crear</title>
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
	
		<div class="container ">
		<div class = "row mt-3 ">
			<div class="col-4 offset-4 mt-5">
				<h2>Crear nueva tarea</h2>
	
				<form:form method="POST" action="/tarea/new"
						modelAttribute="tarea">
					<form:input type="hidden" path="creador" value="${usuario.id}" />
					
					<div>
						<form:errors class="text-danger" path="task" />
						<form:label path="task">Task: </form:label>
						<form:input type="text" path="task" class="form-control"/>
					</div>
					
					<div class="form-group">
						<form:label  path="asistente">Asignar:  </form:label>
							<form:select  path="asistente" class="form-control">
								<c:forEach items="${usuarios}" var="usuarios">
									<form:option value="${usuarios.id }">${usuarios.name }</form:option>
								</c:forEach>	
						</form:select>
					</div>
					
					<div >
					<form:errors class="text-danger" path="priority" />
						<form:label path="priority">priority: </form:label>
						<form:select path="priority" type="text" class="form-control">
				
						<form:option value="0"> Select</form:option>
						<form:option value="1"> Low</form:option>
						<form:option value="2">Medium</form:option>
						<form:option value="3">High</form:option>
			
						</form:select>
					 </div>
	
					<input type="submit" value="Crear task" class = "mt-2" />
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>