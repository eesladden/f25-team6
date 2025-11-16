<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Card List</title>
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
    <!--Breadcrumbs Start-->
    <nav aria-label="breadcrumb" class="mt-4 ms-5">
        <ol class="breadcrumb ms-3">
            <li class="breadcrumb-item"><a href="/providers/dashboard">Dashboard</a></li>
            <li class="breadcrumb-item"><a href="/cards/collection">My Collection</a></li>
            <li class="breadcrumb-item active" aria-current="page">Card List</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!---Card List-->
    <div class="container mt-4">
        <h1 class="mb-3">Card List</h1>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <!--Search Bar-->
            <div class="col mb-3 d-flex w-100 gap-2">
                <div class="col mt-2">
                    <form id="searchForm" action="/cards/search" method="get" class="searchForm" style="width: 845px;">
                        <input type="text" name="name" id="searchBar" class="form-control" placeholder="Search cards by name">
                    </form>
                </div>
                <a href="#" onclick="document.getElementById('searchForm').submit();" class="btn btn-primary mt-2 w-100" style="width: 100px;">Search</a>
                <a href="/cards" class="btn btn-dark mt-2 w-100" style="margin-right: 25px;">Clear Search</a>
            </div>
            <!--Filter Form-->
            <div class="row mb-4 mt-2 w-100">
                <div class="col">
                    <form id="filterForm" action="/cards/filter" method="get" class="filterForm row g-2">
                        <div class="col">
                            <a href="/cards/new" class="createCardButtonList btn btn-success w-100">Create New Card</a>
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
                            <button type="submit" class="applyFilters btn btn-info flex-fill w-100" style="width: 100%;">Apply Filters</button>
                        </div>
                        <div class="col">
                            <a href="/cards" class="clearFilters btn btn-secondary flex-fill w-100" style="width: 100%;">Clear Filters</a>
                        </div>
                    </form> 
                </div>
            </div>
            <!---Card List-->
            <#list cards as card>
            <div class="col mt-2">
                <a href="/cards/view/${card.id}" class="text-decoration-none text-dark">
                    <div class="card h-100">
                        <img src="/card-images/${(card.imagePath)!'image-not-found.png'}" class="card-img-top" alt="${card.name}">
                        <div class="card-body">
                            <h5 class="card-title">${card.name}</h5>
                            <p class="card-text">Game: ${card.game}</p>
                            <p class="card-text">Set: ${card.set}</p>
                            <p class="card-text">Rarity: ${card.rarity}</p>
                        </div>
                        <div class="card-footer text-center d-flex gap-2">
                            <a href="/cards/${card.id}/edit" class="btn btn-primary flex-fill" style="margin-left: -8px;">Edit Card</a>
                            <form id="addToCollectionForm" action="/cards/${card.id}/addToProvider" method="post" class="d-inline">
                                <button type="submit" class="btn btn-success flex-fill w-100">Add to My Collection</button>
                            </form>
                        </div>
                    </div>
                </a>
            </div>
            </#list>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/card.js"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>
                        
                        