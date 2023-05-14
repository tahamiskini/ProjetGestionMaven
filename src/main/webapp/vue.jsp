<%@page import="com.dao.Produit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="my-3">
<a href="addProduit" class="btn btn-primary">Ajouter un nouveau produit</a>
</div>
<% List<Produit> liste=(List<Produit>)request.getAttribute("listeP"); %>
<table class="table table-hover">
<tr>
<th>#</th>
<th>Nom</th>
<th>Prix</th>
<th>Quantite</th>
<th>Categorie</th>
<th>Action</th>
</tr>
<c:forEach var="p" items="${listeP}">
<tr>
<td>${p.id}</td>
<td>${p.nom}</td>
<td>${p.prix}</td>
<td>${p.quantite}</td>
<td>${p.categorie.nom}</td>
<td><a href="suppProduit?id=${p.id}" onClick="return confirm('Voulez vous vraiment supprimer ?')" class="btn btn-danger">Supprimer</a></td>
<td><a href="modifProduit?id=${p.id}" class="btn btn-warning"> Modifier</a></td>
</tr>
</c:forEach>
</table>
 

</body>
</html>