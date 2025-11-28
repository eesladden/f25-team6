<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Listing Details</title>
    <link rel="stylesheet" type="text/css" href="/css/provider-style.css">
</head>
<body style="background-image: url(&quot;/provider-images/${(provider.bgImagePath)!'tcg-bg.jpg'}&quot;); background-size: cover; background-position: center; background-repeat: no-repeat; background-attachment: fixed; background-color: rgba(255, 255, 255, 0.8); background-blend-mode: lighten;">
    <!-- NAVBAR Start-->
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
    <!-- NAVBAR End -->
    <!-- Breadcrumbs Start -->
    <nav aria-label="breadcrumb" class="mt-4 ms-5">
        <ol class="breadcrumb ms-3">
            <li class="breadcrumb-item"><a href="/providers/dashboard">Dashboard</a></li>
            <li class="breadcrumb-item"><a href="/listings/my-listings">My Listings</a></li>
            <li class="breadcrumb-item active" aria-current="page">Listing Details</li>
        </ol>
    </nav>
    <!-- Breadcrumbs End -->
    <#if listing.forSaleOrTrade == "For Sale">
    <!--For Sale Listing Details-->
    <div class="containerSale py-4 pe-3 border rounded mx-auto mt-5 w-100 d-flex bg-white bg-opacity-100" style="max-width: 900px;">
        <!-- Image -->
        <div class="col-md-5 ps-3">
            <img src="/card-images/${listing.card.imagePath!'image-not-found.png'}" class="card-img-top" style="width: 90%;" alt="${listing.card.name}">
        </div>

        <!-- Details -->
        <div class="col-md-7">
        <h1 class="h4 mb-1">${listing.card.name} — ${listing.condition}</h1>
        <p class="text-secondary small mb-3">${listing.card.game} • ${listing.card.set} • Listing ID #${listing.id}</p>
        <!-- Estimated value box -->
        <div class="value-box mb-3">
            <div class="d-flex justify-content-between small">
            <span>Market</span><strong>$${listing.marketPrice}</strong>
            </div>
            <div class="d-flex justify-content-between small">
            <span>Low / High</span><strong>$${listing.lowPrice} / $${listing.highPrice}</strong>
            </div>
        </div>
        
        <div class="d-flex gap-2 mb-3">
            <a href="/listings/my-listings/${listing.id}/edit" class="btn btn-primary">Edit Listing</a>
            <form id="deleteListingForm" action="/listings/my-listings/${listing.id}/delete" method="post" onsubmit="return confirm('Are you sure you want to delete this listing? This action cannot be undone.');" class="d-inline">
                <button type="submit" class="btn btn-outline-danger">Delete Listing</button>
            </form>
        </div>

        <hr>
        <h2 class="h6">Details</h2>
        <ul class="small">
            <li>Location: ${listing.location}</li>
            <li>Game: ${listing.card.game}</li>
            <li>Set: ${listing.card.set}</li>
            <li>Rarity: ${listing.card.rarity}</li>
            <li>Condition: ${listing.condition}</li>
            <li>Grading: ${listing.grade}</li>
            <li>Seller: ${listing.provider.name}</li>
        </ul>
        </div>
    </div>
    <#else>
    <!--For Trade Listing Details-->
    <div class="containerTrade py-4 pe-3 border rounded mx-auto mt-5 w-100 d-flex bg-white bg-opacity-100" style="max-width: 900px;">
        <!-- Image -->
        <div class="col-md-5 ps-3">
            <img src="/card-images/${listing.card.imagePath!'image-not-found.png'}" class="card-img-top" style="width: 90%;" alt="${listing.card.name}">
        </div>
    <!-- Details -->
        <div class="col-md-7">
        <h1 class="h4 mb-1">${listing.card.name} — ${listing.condition}</h1>
        <p class="text-secondary small mb-3">${listing.card.game} • ${listing.card.set} • Listing ID #${listing.id}</p>
        <!-- Estimated value box -->
        <div class="value-box mb-3">
            <div class="d-flex justify-content-between small">
            <span>Trading For:</span><strong>${listing.tradingFor}</strong>
            </div>
        </div>

        <div class="d-flex gap-2 mb-3">
            <a href="/listings/my-listings/${listing.id}/edit" class="btn btn-primary">Edit Listing</a>
            <form id="deleteListingForm" action="/listings/my-listings/${listing.id}/delete" method="post" onsubmit="return confirm('Are you sure you want to delete this listing? This action cannot be undone.');" class="d-inline">
                <button type="submit" class="btn btn-outline-danger">Delete Listing</button>
            </form>
        </div>

        <hr>
        <h2 class="h6">Details</h2>
        <ul class="small">
            <li>Game: ${listing.card.game}</li>
            <li>Set: ${listing.card.set}</li>
            <li>Rarity: ${listing.card.rarity}</li>
            <li>Condition: ${listing.condition}</li>
            <li>Grading: ${listing.grade}</li>
            <li>Seller: ${listing.provider.name}</li>
        </ul>
        </div>
    </div>
    </#if>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/provider-logout.js"></script>
</body>
</html>