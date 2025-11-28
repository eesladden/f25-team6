<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Card Collection</title>
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
            <li class="breadcrumb-item active" aria-current="page">Card Collection</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!---Card Collection-->
    <div class="container mt-4 mb-5 p-4 bg-white bg-opacity-75 rounded">
        <h1 class="mb-3">My Collection</h1>
        <!--Search Bar-->
        <div class="row mb-3">
            <div class="col d-flex w-100 gap-2 text-center justify-content-center align-items-center">
                <div class="col mt-2">
                    <form id="searchForm" action="/cards/collection/search" method="get" class="searchForm" style="width: 924px;">
                        <input type="text" name="name" id="searchBar" class="form-control" placeholder="Search cards by name">
                    </form>
                </div>
                <a href="#" onclick="document.getElementById('searchForm').submit();" class="btn btn-primary mt-2 w-100" style="width: 100px;">Search</a>
                <a href="/cards/collection" class="btn btn-dark mt-2 w-100">Clear Search</a>
            </div>
        </div>
        <!--Filter Form and Buttons-->
        <div class="row mb-3 text-center justify-content-center align-items-center pb-3">
            <form id="filterForm" action="/cards/collection/filter" method="get" class="filterFormCollection row g-2 w-100" style="margin-left: 6px;">
                <div class="col">
                    <a href="/cards/new" class="createCardButton btn btn-success w-100">Create New Card</a>
                </div>
                <div class="col">
                    <a href="/cards" class="viewAllCardsButton btn btn-primary w-100">View All Cards</a>
                </div>
                <div class="col mt-2">
                    <select id="sortGame" name="game" class="form-select">
                        <option value="" selected>Sort by Game</option>
                        <#assign cardGames = []>
                        <#list cards as card>
                            <#assign cardGames = cardGames + [card.game]>
                        </#list>
                        <#assign seenElements = []>
                        <#list cardGames as game>
                            <#if !(seenElements?seq_contains(game))>
                                <#assign seenElements = seenElements + [game]>
                            </#if>
                        </#list>
                        <#list seenElements as uniqueGame>
                            <option value="${uniqueGame}">${uniqueGame}</option>
                        </#list>
                    </select>
                </div>
                <div class="col mt-2">
                    <select id="sortSet" name="set" class="form-select">
                        <option value="" selected>Sort by Set</option>
                        <#assign cardSets = []>
                        <#list cards as card>
                            <#assign cardSets = cardSets + [card.set]>
                        </#list>
                        <#assign seenElements = []>
                        <#list cardSets as set>
                            <#if !(seenElements?seq_contains(set))>
                                <#assign seenElements = seenElements + [set]>
                            </#if>
                        </#list>
                        <#list seenElements as uniqueSet>
                            <option value="${uniqueSet}">${uniqueSet}</option>
                        </#list>
                    </select>
                </div>
                <div class="col mt-2">
                    <select id="sortRarity" name="rarity" class="form-select">
                        <option value="" selected>Sort by Rarity</option>
                        <#assign cardRarities = []>
                        <#list cards as card>
                            <#assign cardRarities = cardRarities + [card.rarity]>
                        </#list>
                        <#assign seenElements = []>
                        <#list cardRarities as rarity>
                            <#if !(seenElements?seq_contains(rarity))>
                                <#assign seenElements = seenElements + [rarity]>
                            </#if>
                        </#list>
                        <#list seenElements as uniqueRarity>
                            <option value="${uniqueRarity}">${uniqueRarity}</option>
                        </#list>
                    </select>
                </div>
                <div class="col">
                    <button type="submit" class="applyFiltersCollection btn btn-info w-100">Apply Filters</button>
                </div>
                <div class="col me-2">
                    <a href="/cards/collection" class="clearFiltersCollection btn btn-secondary w-100">Clear Filters</a>
                </div>
            </form>
        </div>
        <#if cards?size == 0>
            <p class="mt-4 mb-2">You have no cards in your collection. <a href="/cards">Browse cards</a> to add to your collection!</p>
        <#else>
        <!---Card List-->
        <div class="row mt-4">
            <#list cards as card>
                <div class="col-md-4 mb-4">
                    <a href="/cards/view/${card.id}" class="text-decoration-none text-dark">
                        <div class="card h-100">
                            <img src="/card-images/${(card.imagePath)!'image-not-found.png'}" class="card-img-top" alt="${card.name}">
                            <div class="card-body">
                                <h5 class="card-title">${card.name}</h5>
                                <p class="card-text">Game: ${card.game}</p>
                                <p class="card-text">Set: ${card.set}</p>
                                <p class="card-text">Rarity: ${card.rarity}</p>
                            </div>
                            <div class="card-footer text-center d-flex gap-2 justify-content-center">
                                <a href="/listings/my-listings/new/card/${card.id}" class="btn btn-primary flex-fill">Create Listing</a>
                                <form id="removeCardForm" action="/cards/${card.id}/removeFromProvider" method="post" class="d-inline">
                                    <button type="submit" class="btn btn-danger flex-fill me-2">Remove from Collection</button>
                                </form>
                            </div>
                        </div>
                    </a>
                </div>
            </#list>
        </div>
        </#if>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/card.js"></script>
    <script src="/js-provider/provider-logout.js"></script>
</body>
</html>