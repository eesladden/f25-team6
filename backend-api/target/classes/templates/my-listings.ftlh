<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - My Listings</title>
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
    <!---My Listings Content-->
    <div class="container mt-4">
        <h2>My Listings</h2>
        <div class="row">
            <div class="mb-3">
                <input type="text" id="searchBar" class="form-control" placeholder="Search listings by card name">
            </div>
            <#if listings?size == 0>
                <p>You have no listings. <a href="/listings/new">Create a listing</a> to get started!</p>
            <#else>
                <#list listings as listing>
                    <div class="col-md-4 mb-4">
                        <div class="card h-100">
                            <img src="/card-images/${(listing.card.imagePath)!'default-listing.png'}" class="card-img-top" alt="${listing.card.name}">
                            <div class="card-body">
                                <h5 class="card-title">${listing.card.name}</h5>
                                <p class="card-text">Grade: ${listing.grade! 'N/A'}</p>
                                <p class="card-text">Condition: ${listing.condition! 'N/A'}</p>
                                <p class="card-text">${listing.forSaleOrTrade}</p>
                            </div>
                            <div class="card-footer">
                                <a href="/listings/${listing.id}/edit" class="btn btn-primary">Edit Listing</a>
                                <form action="/listings/${listing.id}/delete" method="post" class="d-inline">
                                    <button type="submit" class="btn btn-danger">Delete Listing</button>
                                </form>
                            </div>
                            <div class="card-footer">
                                <a href="/listings/${listing.id}" class="btn btn-secondary">View Listing</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/listing.js"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>