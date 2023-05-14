<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier un Produit</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container">
 <h1>Modifier un produit</h1>
 <form method="post" action="modifProduit">
   <input type="hidden" name="id" value ="${produit.id}">
      <div class="mb-3 mt-3">
      <label for="nom" class="form-label">Nom :</label>
     <input type="text" class="form-control" id="nom" value ="${produit.nom}" name="nom" required>
     </div>
     <div class="mb-3 mt-3">
      <label for="prix" class="form-label">Prix :</label>
      <input type="number" step="0.1" class="form-control" id="prix" value ="${produit.prix}" name="prix" required>
      </div>
      <div class="mb-3 mt-3">
      <label for="quantite" class="form-label">Quantité :</label>
       <input type="number" step="0.1" class="form-control" id="quantite" value ="${produit.quantite}" name="quantite" required>
       </div>
       <div class="mb-3 mt-3">
       <input type="submit" class="btn btn primary" value="Modifier">
       </div>
       </form>
      </div>
      
      
     
      

</body>
</html>