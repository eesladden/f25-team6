<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - All Available Listings</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <!---NAVBAR Start-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/providers/dashboard">TradeTable</a>
            <!--Active listings page-->
            <a class="listingNavActive nav-link align-items-start" style="margin-left: -1200px;" href="/listings">Listings</a>
            <form id="logoutForm" action="/providers/logout" method="post" class="logoutForm d-inline">
                <button type="submit" class="logoutButton btn btn-outline-danger">Logout</button>
            </form>
            <a class="nav-link" href="/providers/profile">
                <img src="/profile-pictures/${(provider.profileImagePath)!'default-profile.png'}" alt="Profile" width="30" height="30" class="d-inline-block align-top">
            </a>
        </div>
    </nav>
    <!--Breadcrumbs Start-->
    <nav aria-label="breadcrumb" class="mt-4 ms-5">
        <ol class="breadcrumb ms-3">
            <li class="breadcrumb-item"><a href="/providers/dashboard">Dashboard</a></li>
            <li class="breadcrumb-item active" aria-current="page">Listings</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!---NAVBAR End-->
    <!--All Available Listings-->
    <div class="container mt-4">
        <h2 class="mb-4">All Available Listings</h2>
        <div class="row mb-4" color="lightgray" style="background-color: lightgray; padding: 15px; border-radius: 5px; padding-bottom: 30px;">
            <!--Search Bar-->
            <div class="col mb-3 mt-2 d-flex w-100 gap-2">
                <div class="col mt-2">
                    <form id="searchForm" action="/listings/search" method="get" class="searchForm" style="width: 950px;">
                        <input type="text" name="name" id="searchBar" class="form-control" placeholder="Search listings by name">
                    </form>
                </div>
                <a href="#" onclick="document.getElementById('searchForm').submit();" class="btn btn-primary mt-2 w-100" style="width: 100px;">Search</a>
                <a href="/listings" class="btn btn-dark mt-2 w-100" style="margin-right: 25px;">Clear Search</a>
            </div>
            <!--Sorts-->
            <div class="row mb-2 mt-2 w-100">
                <div class="col">
                    <form id="filterForm" action="/listings/filter" method="get" class="filterForm row g-2">
                        <div class="col">
                            <a href="/listings/recent" class="createListingButtonList btn btn-success w-100">Most Recent</a>
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
                            <a href="/listings" class="clearFilters btn btn-secondary flex-fill w-100" style="width: 100%;">Clear Filters</a>
                        </div>
                    </form> 
                </div>
            </div>
            <form id="sortForm" action="/listings/sort" method="get" class="sortForm row g-2">
                <div class="col ms-2">
                    <select id="sortBy" name="sortBy" class="form-select">
                        <option value="" selected>Sort By</option>
                        <option value="marketPrice">Market Price</option>
                        <option value="listingPrice">High Price</option>
                        <option value="createdAt">Low Price</option>
                    </select>   
                </div>
                <div class="col">
                    <select id="order" name="order" class="form-select">
                        <option value="" selected>Order</option>
                        <option value="asc">Ascending</option>
                        <option value="desc">Descending</option>
                    </select>
                </div>
                <div class="col">
                    <button type="submit" class="applySort btn btn-info flex-fill w-100" style="width: 100%;">Apply Sort</button>
                </div>
                <div class="col me-4">
                    <a href="/listings" class="clearSort btn btn-secondary flex-fill w-100" style="width: 100%;">Clear Sort</a>
                </div>
            </form>
        </div>
        
        <#if listings?size == 0>
            <p>There are no available listings at this time.</p>
        <#else>
            <#list listings as listing>
                <div class="col-md-4 mb-4">
                    <a href="/listings/${listing.id}" style="text-decoration: none; color: inherit;">
                        <div class="card h-100">
                            <img src="/card-images/${(listing.card.imagePath)!'default-listing.png'}" class="card-img-top" alt="${listing.card.name}">
                            <div class="card-body">
                                <h5 class="card-title">${listing.card.name}</h5>
                                <p class="card-text">Game: ${listing.card.game}</p>
                                <p class="card-text">${listing.forSaleOrTrade}</p>
                                <p class="card-text mt-3">Provider: <a href="/listings/provider/${listing.provider.username}">${listing.provider.username}</a></p>
                            </div>
                        </div>
                    </a>
                </div>
            </#list>
        </#if>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/listing.js"></script>
    <script src="/js-provider/logout.js"></script>
</body>