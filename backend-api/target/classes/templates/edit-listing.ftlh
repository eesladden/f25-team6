<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Edit Listing</title>
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
    <!--Different for For Trade or For Sale-->
    <#if listing.forSaleOrTrade == "For Sale">
        <div class="container d-flex justify-content-center align-items-start align-items-md-center">
            <div class="edit-listing-form-container card shadow-sm p-4 signup-card mt-3 mt-md-0 listing-container">
                <h2 class="h5 mb-3 text-center">Edit Listing</h2>

                <form class="edit-listing-form row align-items-center justify-content-center" action="/listings/${listing.id?c}/update" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="id" name="id" value="${listing.id?c}">
                    <input type="hidden" id="card" name="card" value="${listing.card}">
                    <input type="hidden" id="provider" name="provider" value="${listing.provider}">

                    <div class="col-md-9">
                        <div class="row mb-3">
                            <div class="card-condition-container col">
                                <label for="condition">Card Condition:</label>
                                <input type="text" id="condition" name="condition" placeholder="Condition"class="form-control" value="${listing.condition!}" required>
                            </div>
                            <div class="card-grade-container col">
                                <label for="card-grade">Card Grade:</label>
                                <input type="text" id="card-grade" name="card-grade" placeholder="Grade" class="form-control" value="${listing.cardGrade!} ">
                            </div>
                        </div>
                        <#if listing.forSaleOrTrade == "For Sale">
                        <div class="row mb-3">
                            <div class="card-market-price-container col">
                                <label for="card-market-price">Market Price:</label>
                                <div class="d-flex">
                                    <label class="dollar-sign">$</label><input type="number" id="card-market-price" name="card-market-price" value="${listing.marketPrice!}" placeholder="0.00" min="0" step="0.01" class="form-control" required>
                                </div>
                            </div>
                            <div class="card-price-high-low-inputs-container col-md-6">
                                <label for="card-price-high">Price Range:</label>
                                <div class="d-flex">
                                    <label class="dollar-sign">$</label><input type="number" id="card-price-low" name="card-price-low" value="${listing.lowPrice!}" placeholder="Low" min="0" step="0.01" class="form-control me-2" required>
                                    <label class="dollar-sign">$</label><input type="number" id="card-price-high" name="card-price-high" value="${listing.highPrice!}" placeholder="High" min="0" step="0.01" class="form-control" required>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="isAvailable" name="isAvailable" value="${listing.isAvailable!}">
                        <div class="row mb-3">
                            <input type="hidden" id="forSaleOrTrade" name="forSaleOrTrade" value="For Sale">
                            <input type="hidden" id="tradingFor" name="tradingFor" value="N/A">
                            <div class="card-location-container col">
                                <label for="location">Location (City, State):</label>
                                <input type="text" id="location" name="location" placeholder="City, State" class="form-control" value="${listing.location!}" required>
                            </div>
                        </div>
                        <#else>
                        <input type="hidden" id="card-market-price" name="card-market-price" value="0.00">
                        <input type="hidden" id="card-price-low" name="card-price-low" value="0.00">
                        <input type="hidden" id="card-price-high" name="card-price-high" value="0.00">
                        <div class="row mb-3">
                            <div class="card-trading-for-container col">
                                <label for="trading-for">Trading For:</label>
                                <input type="text" id="trading-for" name="trading-for" placeholder="Trading For" class="form-control" value="${listing.tradingFor!}" required>
                            </div>
                            <input type="hidden" id="isAvailable" name="isAvailable" value="${listing.isAvailable!}">
                            <input type="hidden" id="forSaleOrTrade" name="forSaleOrTrade" value="For Trade">
                            <div class="card-location-container col">
                                <label for="location">Location (City, State):</label>
                                <input type="text" id="location" name="location" placeholder="City, State" class="form-control" value="${listing.location!}" required>
                            </div>
                        </div>
                        </#if>
                    </div>
                    <div class="button-container text-center mb-3 mt-4">
                        <button class="btn btn-primary w-50" type="submit">Edit Listing</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>