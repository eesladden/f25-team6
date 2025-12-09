<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Create Listing</title>
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
    <!--Breadcrumb Start-->
    <nav aria-label="breadcrumb" class="mt-4 ms-5">
        <ol class="breadcrumb ms-3">
            <li class="breadcrumb-item"><a href="/providers/dashboard">Dashboard</a></li>
            <li class="breadcrumb-item"><a href="/cards">Cards</a></li>
            <li class="breadcrumb-item"><a href="/cards/view/${card.id}">Card Details</a></li>
            <li class="breadcrumb-item active" aria-current="page">Create Listing</li>
        </ol>
    </nav>
    <!--Breadcrumb End-->
    <!--Create Listing Form-->
    <div class="container d-flex justify-content-center align-items-start align-items-md-center mt-4 mb-5">
        <div class="create-listing-form-container card shadow-sm p-4 signup-card mt-3 mt-md-0 listing-container">
            <h2 class="h5 mb-3 text-center">Create Listing</h2>
            <form class="create-listing-form row align-items-center justify-content-center" action="/listings/create/card/${card.id}" method="post" enctype="multipart/form-data" novalidate>
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
                            <input type="text" id="condition" name="condition" placeholder="Fair" class="form-control" required>
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Card condition is required.</div>
                            </#if>
                        </div>
                        <div class="card-grade-container col">
                            <label for="card-grade">Card Grade:</label>
                            <input type="text" id="card-grade" name="grade" placeholder="Gem Mint" class="form-control">
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
                            <div class="col align-items-center d-flex gap-2">
                                <label class="dollar-sign">$</label><input type="number" id="card-market-price" name="marketPrice" value="0.00" placeholder="2.00" min="0" step="0.01" class="form-control" required>
                            </div>
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Market price is required. Enter 0 if trading.</div>
                            </#if>
                        </div>
                        <div class="card-price-high-low-inputs-container col-md-6">
                            <label for="card-price-high">Price Range:</label>
                            <div class="flex-fill d-flex gap-2">
                                <div class="col align-items-center d-flex gap-2">
                                    <label class="dollar-sign">$</label><input type="number" id="card-price-low" name="lowPrice" value="0.00" placeholder="1.00" min="0" step="0.01" class="form-control" required>
                                </div>
                                <label class="to-label align-self-center">–</label>
                                <div class="col align-items-center d-flex gap-2">
                                    <label class="dollar-sign">$</label><input type="number" id="card-price-high" name="highPrice" value="0.00" placeholder="3.00" min="0" step="0.01" class="form-control" required>
                                </div>
                            </div>
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Price range is required. Enter 0 – 0 if trading.</div>
                            </#if>
                        </div>
                        <input type="hidden" id="isAvailable" name="isAvailable" value="true">
                    </div>
                    <div class="row mb-3">
                        <div class="card-trading-for-container col">
                            <label for="trading-for">Trading For:</label>
                            <input type="text" id="trading-for" name="tradingFor" value="N/A" placeholder="Squirtle" class="form-control">
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Trading for is required. Enter N/A if for sale.</div>
                            </#if>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="street-address-container col">
                            <label for="street-address">Street Address:</label>
                            <input type="text" id="street-address" name="streetAddress" placeholder="123 Main St" class="form-control" required>
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Street address is required.</div>
                            </#if>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="city-container col">
                            <label for="city">City:</label>
                            <input type="text" id="city" name="city" placeholder="City" class="form-control" required>
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">City is required.</div>
                            </#if>
                        </div>
                        <div class="state-container col">
                            <label for="state">State:</label>
                            <input type="text" id="state" name="state" placeholder="State" class="form-control" required>
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">State is required.</div>
                            </#if>
                        </div>
                        <div class="zip-code-container col">
                            <label for="zip-code">Zip Code:</label>
                            <input type="text" id="zip-code" name="zipCode" placeholder="Zip Code" class="form-control" required>
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Zip code is required.</div>
                            </#if>
                        </div>
                        <div class="country-container col">
                            <label for="country">Country:</label>
                            <input type="text" id="country" name="country" placeholder="Country" class="form-control" required>
                            <#if creationError?? && creationError>
                                <div class="text-danger mt-1">Country is required.</div>
                            </#if>
                        </div>
                    </div>
                    <div class="justify-content-center row mb-3 mt-5 me-4">
                        <button class="btn btn-primary w-50" type="submit">Create Listing</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/provider-logout.js"></script>
</body>
</html>
