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
            <a class="navbar-brand" href="/providers/dashboard">TradeTable</a>
            <a class="listingNav nav-link align-items-start" style="margin-left: -1200px;" href="/listings">Listings</a>
            <form id="logoutForm" action="/providers/logout" method="post" class="logoutForm d-inline">
                <button type="submit" class="logoutButton btn btn-outline-danger">Logout</button>
            </form>
            <a class="nav-link" href="/providers/profile">
                <img src="/profile-pictures/${(provider.profileImagePath)!'default-profile.png'}" alt="Profile" width="30" height="30" class="d-inline-block align-top">
            </a>
        </div>
    </nav>
    <!---NAVBAR End-->
    <!--Breadcrumb Start-->
    <nav aria-label="breadcrumb" class="mt-4 ms-4">
        <ol class="breadcrumb ms-5">
            <li class="breadcrumb-item"><a href="/providers/dashboard">Dashboard</a></li>
            <li class="breadcrumb-item"><a href="/listings">Listings</a></li>
            <li class="breadcrumb-item"><a href="/listings/${listing.id?c}">Listing Details</a></li>
            <li class="breadcrumb-item active" aria-current="page">Edit Listing</li>
        </ol>
    </nav>
    <!--Breadcrumb End-->
    <!--Different for For Trade or For Sale-->
    <!--Edit Listing Form-->
    <div class="container d-flex justify-content-center align-items-start align-items-md-center mt-4 mb-5">
        <div class="create-listing-form-container card shadow-sm p-4 signup-card mt-3 mt-md-0 listing-container">
            <h2 class="h5 mb-3 text-center">Edit Listing</h2>
            <form class="create-listing-form row align-items-center justify-content-center" action="/listings/${listing.id}/update" method="post" enctype="multipart/form-data" novalidate>
                <div class="col-md-9">
                    <div class="row mb-3">
                        <div class="card-condition-container col">
                            <label for="condition">Card Condition:</label>
                            <input type="text" id="condition" name="condition" placeholder="${listing.condition!'Fair'}" class="form-control" value="${listing.condition}" required>
                            <#if editError??>
                                <div class="text-danger mt-1">Card condition cannot be empty.</div>
                            </#if>
                        </div>
                        <div class="card-grade-container col">
                            <label for="card-grade">Card Grade:</label>
                            <input type="text" id="card-grade" name="grade" placeholder="${listing.grade!'Gem Mint'}" class="form-control" value="${listing.grade}" required>
                            <#if editError??>
                                <div class="text-danger mt-1">Card grade cannot be empty.</div>
                            </#if>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="card-sale-or-trade-container col mt-2">
                            <label for="forSaleOrTrade">For Sale or For Trade?</label>
                            <#if listing.forSaleOrTrade == "For Sale">
                            <select id="forSaleOrTrade" name="forSaleOrTrade" required>
                                <option value="For Sale">For Sale</option>
                                <option value="For Trade">For Trade</option>
                            </select>
                            <#else>
                            <select id="forSaleOrTrade" name="forSaleOrTrade" required> 
                                <option value="For Trade">For Trade</option>
                                <option value="For Sale">For Sale</option>
                            </select>
                            </#if>
                        </div>
                        <div class="card-market-price-container col">
                            <label for="card-market-price">Market Price:</label>
                            <div class="d-flex">
                                <label class="dollar-sign">$</label><input type="number" id="card-market-price" name="marketPrice" value="${listing.marketPrice}" placeholder="${listing.marketPrice!'2.00'}" min="0" step="0.01" class="form-control" required>
                            </div>
                            <#if editError??>
                                <div class="text-danger mt-1">Market price cannot be empty. Enter 0 if trading.</div>
                            </#if>
                        </div>
                        <div class="card-price-high-low-inputs-container col-md-6">
                            <label for="card-price-high">Price Range:</label>
                            <div class="flex-fill d-flex gap-2">
                                <div class="col align-items-center d-flex gap-2">
                                    <label class="dollar-sign">$</label><input type="number" id="card-price-low" name="lowPrice" value="${listing.lowPrice}" placeholder="${listing.lowPrice!'1.00'}" min="0" step="0.01" class="form-control" required>
                                </div>
                                <label class="to-label align-self-center">–</label>
                                <div class="col align-items-center d-flex gap-2">
                                    <label class="dollar-sign">$</label><input type="number" id="card-price-high" name="highPrice" value="${listing.highPrice}" placeholder="${listing.highPrice!'3.00'}" min="0" step="0.01" class="form-control" required>
                                </div>
                            </div>
                            <#if editError??>
                                <div class="text-danger mt-1">Price range cannot be empty. Enter 0 – 0 if trading.</div>
                            </#if>
                        </div>
                        <input type="hidden" id="isAvailable" name="isAvailable" value="true">
                    </div>
                    <div class="row mb-3">
                        <div class="card-trading-for-container col">
                            <label for="trading-for">Trading For:</label>
                            <input type="text" id="trading-for" name="tradingFor" value="${listing.tradingFor}" placeholder="${listing.tradingFor!'Squirtle'}" class="form-control">
                            <#if editError??>
                                <div class="text-danger mt-1">Trading for cannot be empty. Enter N/A if for sale.</div>
                            </#if>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="card-location-container col">
                            <label for="location">Location (City, State):</label>
                            <input type="text" id="location" name="location" value="${listing.location}" placeholder="${listing.location!'Los Angeles, CA'}" class="form-control" required>
                            <#if editError??>
                                <div class="text-danger mt-1">Location cannot be empty.</div>
                            </#if>
                        </div>
                    </div>
                </div>
                <div class="button-container text-center mb-3 mt-4">
                    <button class="btn btn-primary w-50" type="submit">Edit Listing</button>
                </div>
            </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>