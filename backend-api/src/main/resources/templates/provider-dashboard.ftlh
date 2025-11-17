<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Provider Dashboard</title>
    <link rel="stylesheet" type="text/css" href="/css/provider-style.css">
</head>
<body>
    <!---NAVBAR Start-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/providers/dashboard">TradeTable</a>
            <a class="listingNav nav-link align-items-start" style="margin-left: -1200px;" href="/listings">Listings</a>
            <form id="logoutForm" action="/providers/logout" method="post" class="logoutFormNS d-inline">
                <button type="submit" class="logoutButton btn btn-outline-danger">Logout</button>
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
            <li class="breadcrumb-item"><a href="/providers/profile">Profile</a></li>
            <li class="breadcrumb-item active" aria-current="page">Provider Dashboard</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!--Provider Dashboard-->
    <div class="container mt-4">
        <h1 class="mb-4">Provider Dashboard</h1>
        <div class="row">
            <div class="col-md-6 mb-4">
                <div class="card h-100">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">Your Collection</h5>
                        <p class="card-text flex-grow-1">Manage and view all the cards in your collection.</p>
                        <a href="/cards/collection" class="btn btn-primary mt-auto">Go to Collection</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-4">
                <div class="card h-100">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">Your Listings</h5>
                        <p class="card-text flex-grow-1">Manage and view all your current listings.</p>
                        <a href="/listings/my-listings" class="btn btn-secondary mt-auto">Go to Listings</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="/js-provider/provider-logout.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>