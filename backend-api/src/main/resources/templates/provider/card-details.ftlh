<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Card Details</title>
    <link rel="stylesheet" type="text/css" href="/css/provider-style.css">
</head>
<body style="background-image: url(&quot;/provider-images/${(provider.bgImagePath)!'tcg-bg.jpg'}&quot;); background-size: cover; background-position: center; background-repeat: no-repeat; background-attachment: fixed; background-color: rgba(255, 255, 255, 0.8); background-blend-mode: lighten;">
    <!---NAVBAR Start-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/providers/dashboard">TradeTable</a>
            <form id="logoutForm" action="/providers/logout" class="position-absolute end-0 me-5 pe-2" method="post">
                <button type="submit" class="btn btn-outline-danger">Logout</button>
            </form>
            <a class="nav-link" href="/providers/profile">
                <img src="/profile-pictures/${(provider.profileImagePath)!'default-profile.png'}" alt="Profile" width="30" height="30" class="d-inline-block align-top">
            </a>
        </div>
    </nav>
    <!---NAVBAR End-->
    <!--Breadcrumbs Start-->
    <nav aria-label="breadcrumb" class="mt-4 ms-5">
        <ol class="breadcrumb ms-3">
            <li class="breadcrumb-item"><a href="/providers/dashboard">Dashboard</a></li>
            <li class="breadcrumb-item"><a href="/cards/collection">My Collection</a></li>
            <li class="breadcrumb-item"><a href="/cards">Card List</a></li>
            <li class="breadcrumb-item active" aria-current="page">Card Details</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
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
                <#assign cardProvider = card.providers?filter(p -> p.id == provider.id)>
                <#if cardProvider?size == 0>
                    <form id="addToCollectionForm" action="/cards/${card.id}/addToProvider" method="post" class="d-inline">
                        <button type="submit" class="btn btn-success">Add to My Collection</button>
                    </form>
                <#else>
                    <button type="button" class="btn btn-secondary flex-fill me-2" disabled>Add to My Collection</button>
                </#if>
                <a href="/listings/my-listings/new/card/${card.id}" class="btn btn-secondary">Create Listing</a>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <scipt src="/js-provider/card.js"></script>
    <script src="/js-provider/provider-logout.js"></script>
</body>
</html>