<%@page import="java.util.List"%>
<%@page import="com.dao.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Liste Des Clients</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
        crossorigin="anonymous">
</head>

<body>

<div class="container-fluid">
    <div class="row justify-content-center">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Gestion Des Clubs</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                    <c:if test="${role == 'admin'}">
                      <li class="nav-item">
                            <a class="nav-link" href="listeFaculte">Facult�s</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="listeClub">Clubs</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="listeUser">Utilisateurs</a>
                        </li>
                        </c:if>
                            <li class="nav-item">
                            <a class="nav-link" href="listeEvent">Events</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="logout">D�connexion</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>


<main class="container">
    <div class="row justify-content-center mt-3">
        <div class="col-12">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h2 class="h2">Liste Des Utilisateurs</h2>
            </div>

            <form action="rechercherUser" method="post">
                <div class="input-group mb-3">
                    <input type="text" name="rech" class="form-control" placeholder="Recherche Par Nom d'utilisateur">
                    <button type="submit" class="btn btn-secondary">Rechercher</button>
                </div>
            </form>

            <div class="d-flex flex-wrap justify-content-center mt-3">
                <c:forEach var="c" items="${listeU}">
                    <div class="card m-2" style="width: 16rem;">
                                                <div class="card-body d-flex justify-content-center flex-column text-center">
                            <h5 class="card-title">${c.username}</h5>
                            <p class="card-text">${c.role}</p>
                            <div class="d-flex justify-content-center">
                                <a href="modifierUser?id=${c.id}" class="btn btn-primary">Modifier</a>
                                <a onclick="return confirm('Voulez vous vraiment supprimer cet utilisateur?')" href="supprimerUser?id=${c.id}" class="btn btn-danger">Supprimer</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 1}">
                    <li class="page-item"><a class="page-link" href="?page=${currentPage - 1}">&laquo;</a></li>
                </c:if>
                <c:forEach var="page" begin="1" end="${totalPages}">
                    <li class="page-item ${page == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?page=${page}">${page}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item"><a class="page-link" href="?page=${currentPage + 1}">&raquo;</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</main>

</body>
</html>

