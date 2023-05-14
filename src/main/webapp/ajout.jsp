<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter Produit</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
 <h1>Ajouter un produit</h1>
 <form method="post" action="addProduit">
   <div class="mb-3 mt-3">
     <label for="nom" class="form-label">Nom :</label><br/>
     <input type="text" class="form-control" id="nom" name="nom" required>
   </div>
    <div class="mb-3 mt-3">
     <label for="prix" class="form-label">Prix :</label><br/>
     <input type="number" step="0.1" class="form-control" id="prix"  name="prix" required>
   </div>
    <div class="mb-3 mt-3">
     <label for="quantite" class="form-label">Quantité :</label><br/>
     <input type="number" class="form-control" id="quantite"  name="quantite" required>
   </div>
   <div class="mb-3 mt-3">
     <label for="categorie" class="form-label">Catégorie :</label><br/>
     <select name="idact" class="form-select">
       <c:forEach var="cat" items="${categories}">
         <option value="${cat.id}">${cat.nom}</option>
         </c:forEach>
     </select>
   </div>
   
   <div class="mb-3 mt-3">
    <button type="submit" class="btn btn-primary" >Ajouter</button> >
   </div>
 </form>
</div>
</body>
</html>