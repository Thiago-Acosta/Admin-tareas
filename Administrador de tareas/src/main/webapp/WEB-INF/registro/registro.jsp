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
<title>Registro</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
		<div class="container">
			<div class = "row mt-5 ">
				<div class="col-4 offset-3 mt-5">
					<h2 >Registrarse</h2>
					
					<form:form method="POST" action="/registro" modelAttribute="user">
						<div class="form-group">
							<form:errors class="text-danger form-control is-invalid" path="name" />
							<form:label path="name" class="col-lg-4 control-label" >Name:</form:label>
							<form:input path="name" class="col-lg-4 form-control" placeholder="Name"/>
						</div>
						<div class="form-group">
							<form:errors class="text-danger form-control is-invalid" path="email" />
							<form:label path="email" class="col-lg-4 control-label" >Email:</form:label>
							<form:input type="email" path="email" class="col-lg-4 form-control" placeholder="Name@example.com"/>
						</div>
						<div class="form-group">
							<form:errors class="text-danger form-control is-invalid" path="password" />
							<form:label path="password" class="col-lg-4 control-label" >Password:</form:label>
							<form:password path="password" class="col-lg-4 form-control" placeholder="Debe contener minimo 8 caracteres"/>
						</div>
						<div class="form-group">
							<form:errors class="text-danger form-control is-invalid" path="passwordConfirmation" />
							<form:label path="passwordConfirmation" class="col-lg-4 control-label" >Password:</form:label>
							<form:password path="passwordConfirmation" class="col-lg-4 form-control" placeholder="Debe contener minimo 8 caracteres"/>
						</div>
						<input type="submit" value="Registrarse" class = "mt-2"/>
					</form:form>
					<p class="text-success"> <c:out value="${registro }"></c:out> </p>
					<p class = "text-center">Volver a<a href = "/login"> inicio de sesion</a></p>
				</div>
			</div>
		</div>

</body>
</html>