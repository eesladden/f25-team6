<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Listing Details</title>
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
    <#if listing.forSaleOrTrade == "For Sale">
    <!--For Sale Listing Details-->
    <div class="container py-4">
        <!-- Image -->
        <div class="col-md-5">
            <img src="/card-images/${listing.card.imagePath!'image-not-found.png'}" class="card-img-top" alt="${listing.card.name}">
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
            <div class="text-secondary tiny">Created: ${listing.createdAt?string("yyyy-MM-dd HH:mm")} • Updated: ${listing.updatedAt?string("yyyy-MM-dd HH:mm")}</div>
        </div>

        <div class="d-flex gap-2 mb-3">
            <a href="#" class="btn btn-primary">Make Offer</a>
        </div>

        <hr>
        <h2 class="h6">Details</h2>
        <ul class="small">
            <li>Location: ${listing.cityName}, ${listing.stateName}</li>
            <li>Game: ${listing.card.game}</li>
            <li>Set: ${listing.card.set}</li>
            <li>Rarity: ${listing.rarity}</li>
            <li>Condition: ${listing.condition}</li>
            <li>Grading: ${listing.grade}</li>
            <li>Seller: ${listing.provider.name}</li>
        </ul>
        </div>
    </div>
    <#else>
    <!--For Trade Listing Details-->
        <!-- Image -->
        <div class="col-md-5">
            <img src="/card-images/${listing.card.imagePath!'image-not-found.png'}" class="card-img-top" alt="${listing.card.name}">
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
            <a href="#" class="btn btn-primary">Propose Trade</a>
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
</body>
</html>