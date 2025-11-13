<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Card Collection</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <!---NAVBAR Start-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/home">TradeTable</a>
            <form id="logoutForm" action="/providers/logout" method="post" class="d-inline">
                <button type="submit" class="btn btn-outline-danger">Logout</button>
            </form>
            <a class="nav-link" href="/providers/profile">
                <!--should be the profile picture of the provider in session-->
                <img src="/profile-pictures/${(provider.profileImagePath)!'default-profile.png'}" alt="Profile" width="30" height="30" class="d-inline-block align-top">
            </a>
        </div>
    </nav>
    <!---NAVBAR End-->

    <!---Card Collection-->
    <div class="container mt-4">
        <h1 class="mb-4">Collection</h1>
        <div class="row mb-4">
            <input type="text" id="searchBar" class="form-control" placeholder="Search cards by name">
        </div>
        <div class="row row-cols-1 row-cols-md-3 g-4">
                <!--Buttons-->
                <div class="col-md-2">
                    <a href="/cards/new" class="btn btn-success">Create New Card</a>
                </div>
                <div class="col-md-2">
                    <a href="/cards" class="btn btn-primary">View All Cards</a>
                </div>
                <!--Sort-->
                <div class="col-md-2">
                    <select id="filterGame" class="form-select">
                        <option value="">All Games</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <select id="filterSet" class="form-select mt-2">
                        <option value="">All Sets</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <select id="filterRarity" class="form-select mt-2">
                        <option value="">All Rarities</option>
                </select>
            </div>
             <!---Card List-->
            <#list cards as card>
                <div class="col">
                    <a href="/cards/view/${card.id}" class="text-decoration-none text-dark">
                        <div class="card h-100">
                            <img src="/card-images/${(card.imagePath)!'image-not-found.png'}" class="card-img-top" alt="${card.name}">
                            <div class="card-body">
                                <h5 class="card-title">${card.name}</h5>
                            <p class="card-text">Game: ${card.game}</p>
                            <p class="card-text">Set: ${card.set}</p>
                            <p class="card-text">Rarity: ${card.rarity}</p>
                        </div>
                        <div class="card-footer text-end">
                            <a href="/cards/${card.id}/edit" class="btn btn-primary">Edit Card</a>
                            <!--Need this to be removeCardForm using JS-->
                            <form id="removeCardForm" action="/cards/${card.id}/removeFromProvider" method="post" class="d-inline">
                                <button type="submit" class="btn btn-danger">Remove from Collection</button>
                            </form>
                            <a href="/listings/create?cardId=${card.id}" class="btn btn-secondary">Create Listing</a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/card.js"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>