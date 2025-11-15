<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Card Details</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <!---NAVBAR Start-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/providers/dashboard">TradeTable</a>
            <form id="logoutForm" action="/providers/logout" method="post" class="logoutForm d-inline">
                <button type="submit" class="logoutButton btn btn-outline-danger">Logout</button>
            </form>
            <a class="nav-link" href="/providers/profile">
                <!--should be the profile picture of the provider in session-->
                <img src="/profile-pictures/${(provider.profileImagePath)!'default-profile.png'}" alt="Profile" width="30" height="30" class="d-inline-block align-top">
            </a>
        </div>
    </nav>
    <!---NAVBAR End-->

    <!---Card Details-->
    <div class="container mt-4">
        <div class="card mb-4">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="/card-images/${(card.imagePath)!'image-not-found.png'}" class="img-fluid rounded-start" alt="${card.name}">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">${card.name}</h5>
                        <p class="card-text"><strong>Game:</strong> ${card.game}</p>
                        <p class="card-text"><strong>Set:</strong> ${card.set}</p>
                        <p class="card-text"><strong>Rarity:</strong> ${card.rarity}</p>
                    </div>
                </div>
            </div>
            <div class="card-footer text-end">
                <a href="/cards/${card.id}/edit" class="btn btn-primary">Edit Card</a>
                <form id="addToCollectionForm" action="/cards/${card.id}/addToProvider" method="post" class="d-inline">
                    <button type="submit" class="btn btn-success">Add to My Collection</button>
                </form>
                <a href="/listings/new/card/${card.id}" class="btn btn-secondary">Create Listing</a>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <scipt src="/js-provider/card.js"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>