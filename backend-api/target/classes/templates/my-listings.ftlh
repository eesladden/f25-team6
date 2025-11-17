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
            <li class="breadcrumb-item active" aria-current="page">My Listings</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!---My Listings Content-->
    <div class="container mt-4">
        <h2>My Listings</h2>
        <div class="row">
            <!--Search Bar-->
            <div class="col mb-3 mt-2 d-flex w-100 gap-2">
                <div class="col mt-2">
                    <form id="searchForm" action="/listings/my-listings/search" method="get" class="searchForm" style="width: 950px;">
                        <input type="text" name="name" id="searchBar" class="form-control" placeholder="Search listings by name">
                    </form>
                </div>
                <a href="#" onclick="document.getElementById('searchForm').submit();" class="btn btn-primary mt-2 w-100" style="width: 100px;">Search</a>
                <a href="/listings/my-listings" class="btn btn-dark mt-2 w-100" style="margin-right: 25px;">Clear Search</a>
            </div>
            <!--Sorts-->
            <div class="row mb-3 mt-2 w-100">
                <div class="col">
                    <form id="filterForm" action="/listings/my-listings/filter" method="get" class="filterForm row g-2">
                        <div class="col">
                            <a href="/cards" class="createListingButtonList btn btn-success w-100">Find Cards</a>
                        </div>
                        <div class="col mt-2">
                            <select id="sortGame" name="game" class="form-select">
                                <option value="" selected>Sort by Game</option>
                                <#assign cardGames = []>
                                <#list listings as listing>
                                    <#assign cardGames = cardGames + [listing.card.game]>
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
                                <#list listings as listing>
                                    <#assign cardSets = cardSets + [listing.card.set]>
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
                                <#list listings as listing>
                                    <#assign cardRarities = cardRarities + [listing.card.rarity]>
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
                            <select id="sortCondition" name="condition" class="form-select">
                                <option value="" selected>Sort by Condition</option>
                                <#assign cardConditions = []>
                                <#list listings as listing>
                                    <#assign cardConditions = cardConditions + [listing.condition]>
                                </#list>
                                <#assign seenElements = []>
                                <#list cardConditions as condition>
                                    <#if !(seenElements?seq_contains(condition))>
                                        <#assign seenElements = seenElements + [condition]>
                                    </#if>
                                </#list>
                                <#list seenElements as uniqueCondition>
                                    <option value="${uniqueCondition}">${uniqueCondition}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="col mt-2">
                            <select id="sortGrade" name="grade" class="form-select">
                                <option value="" selected>Sort by Grade</option>
                                <#assign cardGrades = []>
                                <#list listings as listing>
                                    <#assign cardGrades = cardGrades + [listing.grade]>
                                </#list>
                                <#assign seenElements = []>
                                <#list cardGrades as grade>
                                    <#if !(seenElements?seq_contains(grade))>
                                        <#assign seenElements = seenElements + [grade]>
                                    </#if>
                                </#list>
                                <#list seenElements as uniqueGrade>
                                    <option value="${uniqueGrade}">${uniqueGrade}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="col">
                            <button type="submit" class="applyFilters btn btn-info flex-fill w-100" style="width: 100%;">Apply Filters</button>
                        </div>
                        <div class="col">
                            <a href="/listings/my-listings" class="clearFilters btn btn-secondary flex-fill w-100" style="width: 100%;">Clear Filters</a>
                        </div>
                    </form> 
                </div>
            </div>
            <div class="row row-cols-1 row-cols-md-3 g-4 w-100 mt-1">
                <#if listings?size == 0>
                    <p>You have no listings. <a href="/cards">Find a card you want to list</a> to get started!</p>
                <#else>
                    <#list listings as listing>
                        <div class="col-md-4 mb-4">
                            <a href="/listings/${listing.id}" class="text-decoration-none text-dark">
                                <div class="card h-100">
                                    <img src="/card-images/${(listing.card.imagePath)!'default-listing.png'}" class="card-img-top" alt="${listing.card.name}"> 
                                    <div class="card-body">
                                        <#if listing.isAvailable == true>
                                        <h5 class="card-title">${listing.card.name} <span class="badge bg-success ms-2 mb-1">Available</span></h5>
                                            <#else>
                                        <h5 class="card-title">${listing.card.name} <span class="badge bg-secondary ms-2 mb-1">Not Available</span></h5>
                                        </#if>
                                        <p class="card-text">Grade: ${listing.grade! 'N/A'}</p>
                                        <p class="card-text">Condition: ${listing.condition! 'N/A'}</p>
                                        <p class="card-text">${listing.forSaleOrTrade}</p>
                                    </div>
                                    <div class="card-footer align-items-center d-flex gap-2">
                                        <a href="/listings/${listing.id}/edit" class="btn btn-primary" style="width: 210px; margin-left: -10px;">Edit Listing</a>
                                        <form action="/listings/${listing.id}/delete" method="post" class="d-inline">
                                            <button type="submit" class="btn btn-danger" style="width: 190px;">Delete Listing</button>
                                        </form>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>