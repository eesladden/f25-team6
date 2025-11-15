<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Card Collection</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
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

    <!---Card Collection-->
    <div class="container mt-4">
        <h1 class="mb-4">Collection</h1>
        <!--Search Bar-->
        <form id="searchForm" action="/cards/collection/search" method="get" class="mb-4">
            <input type="text" name="name" id="searchBar" class="form-control" placeholder="Search cards by name">
        </form>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <!--Buttons-->
            <div class="col-md-2 mt-3">
                <a href="/cards/new" class="createCardButton btn btn-success">Create New Card</a>
            </div>
            <div class="col-md-2 mt-3">
                <a href="/cards" class="viewAllCardsButton btn btn-primary">View All Cards</a>
            </div>
            <form id="filterForm" action="/cards/collection/filter" method="get" class="filterFormCollection row g-2">
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
                <div class="col mt-2">
                    <button type="submit" class="applyFiltersCollection btn btn-info w-200">Apply Filters</button>
                </div>
                <div class="col mt-2">
                    <a href="/cards/collection" class="clearFiltersCollection btn btn-secondary">Clear Filters</a>
                </div>
            </form>
             <!---Card List-->
            <#list cards as card>
                <div class="col">
                    <a href="/cards/view/${card.id}" class="text-decoration-none text-dark">
                        <div class="card h-100">
                            <img src="/card-images/${(card.imagePath)!'image-not-found.png'}" class="card-img-top" alt="${card.name}">
                            <div class="card-body">
                                <h5 class="card-title">${card.name}</h5>
                            <p class="card-text">Game: ${card.game}</p>
                            <p class="card-text">Set: ${card.set}</p>
                            <p class="card-text">Rarity: ${card.rarity}</p>
                        </div>
                        <div class="card-footer text-end">
                            <a href="/cards/${card.id}/edit" class="btn btn-primary">Edit Card</a>
                            <!--Need this to be removeCardForm using JS-->
                            <form id="removeCardForm" action="/cards/${card.id}/removeFromProvider" method="post" class="d-inline">
                                <button type="submit" class="btn btn-danger">Remove from Collection</button>
                            </form>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/card.js"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>