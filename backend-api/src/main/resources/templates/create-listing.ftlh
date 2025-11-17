<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Create Listing</title>
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
    <nav aria-label="breadcrumb" class="mt-4 ms-5">
        <ol class="breadcrumb ms-3">
            <li class="breadcrumb-item"><a href="/providers/dashboard">Dashboard</a></li>
            <li class="breadcrumb-item"><a href="/cards">Cards</a></li>
            <li class="breadcrumb-item"><a href="/cards/${card.id}">Card Details</a></li>
            <li class="breadcrumb-item active" aria-current="page">Create Listing</li>
        </ol>
    </nav>
    <!--Breadcrumb End-->
    <!--Create Listing Form-->
    <div class="container d-flex justify-content-center align-items-start align-items-md-center mt-4 mb-5">
        <div class="create-listing-form-container card shadow-sm p-4 signup-card mt-3 mt-md-0 listing-container">
            <h2 class="h5 mb-3 text-center">Create Listing</h2>
            <form class="create-listing-form row align-items-center justify-content-center" action="/listings/create/card/${card.id}" method="post" enctype="multipart/form-data">
                <div class="col-md-3 text-center mb-3">
                    <img src="/card-images/${(card.imagePath)!'image-not-found.png'}" alt="${card.name}" class="img-fluid mb-2">
                    <h5 class="card-title">${card.name}</h5>
                    <p class="card-text">Game: ${card.game}</p>
                    <p class="card-text">Set: ${card.set}</p>
                    <p class="card-text">Rarity: ${card.rarity}</p>
                </div>
                <div class="col-md-9">
                    <div class="row mb-3">
                        <div class="card-condition-container col">
                            <label for="condition">Card Condition:</label>
                            <input type="text" id="condition" name="condition" placeholder="Condition"class="form-control" required>
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Card condition is required.</div>
                            </#if>
                        </div>
                        <div class="card-grade-container col">
                            <label for="card-grade">Card Grade:</label>
                            <input type="text" id="card-grade" name="grade" placeholder="Grade" class="form-control">
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Card grade is required.</div>
                            </#if>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="card-sale-or-trade-container col mt-2">
                            <label for="forSaleOrTrade">For Sale or For Trade?</label>
                            <select id="forSaleOrTrade" name="forSaleOrTrade" required>
                                <option value="For Sale">For Sale</option>
                                <option value="For Trade">For Trade</option>
                            </select>
                        </div>
                        <div class="card-market-price-container col">
                            <label for="card-market-price">Market Price:</label>
                            <div class="d-flex">
                                <label class="dollar-sign">$</label><input type="number" id="card-market-price" name="marketPrice" value="0.00" placeholder="0.00" min="0" step="0.01" class="form-control" required>
                            </div>
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Market price is required. Enter 0 if trading.</div>
                            </#if>
                        </div>
                        <div class="card-price-high-low-inputs-container col-md-6">
                            <label for="card-price-high">Price Range:</label>
                            <div class="d-flex">
                                <label class="dollar-sign">$</label><input type="number" id="card-price-low" name="lowPrice" value="0.00" placeholder="Low" min="0" step="0.01" class="form-control me-2" required>
                                <#if creationError?? && creationError>
                                    <div class="text-danger mt-1">Low price is required. Enter 0 if trading.</div>
                                </#if>
                                <label class="dollar-sign">$</label><input type="number" id="card-price-high" name="highPrice" value="0.00" placeholder="High" min="0" step="0.01" class="form-control" required>
                                <#if creationError?? && creationError>
                                    <div class="text-danger mt-1">High price is required. Enter 0 if trading.</div>
                                </#if>
                            </div>
                        </div>
                        <input type="hidden" id="isAvailable" name="isAvailable" value="true">
                    </div>
                    <div class="row mb-3">
                        <div class="card-trading-for-container col">
                            <label for="trading-for">Trading For:</label>
                            <input type="text" id="trading-for" name="tradingFor" value="N/A" placeholder="Trading For" class="form-control">
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Trading for is required. Enter N/A if for sale.</div>
                            </#if>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="card-location-container col">
                            <label for="location">Location (City, State):</label>
                            <input type="text" id="location" name="location" placeholder="City, State" class="form-control" required>
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Location is required.</div>
                            </#if>
                        </div>
                    </div>
                </div>
                <div class="button-container text-center mb-3 mt-4">
                    <button class="btn btn-primary w-50" type="submit">Create Listing</button>
                </div>
            </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>
