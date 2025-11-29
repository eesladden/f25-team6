<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - My Trade Offers</title>
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
            <li class="breadcrumb-item"><a href="/providers/profile">Profile</a></li>
            <li class="breadcrumb-item"><a href="/providers/dashboard">Provider Dashboard</a></li>
            <li class="breadcrumb-item active" aria-current="page">My Reviews</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!--Offers-->
    <div class="container mt-4">
        <h1 class="mb-4">My Trade Offers</h1>
        <div class="list-group">
            <#if tradeOffers?size == 0>
                <p>You have no trade offers at the moment.</p>
            <#else>
                <#list tradeOffers as offer>
                    <#if offer.status == "COMPLETED" || offer.status == "REJECTED">
                        <p>All trade offers are completed or rejected.</p>
                    <#else>
                        <div class="list-group-item mb-3 rounded">
                            <#if offer.listing.forSaleOrTrade == "For Sale">
                                <h5>Sale Offer from ${offer.buyer.username} for Listing: <a href="/listings/my-listings/${offer.listing.id}">${offer.listing.card.name} (ID: ${offer.listing.id})</a></h5>
                                <#assign offeredAmountDollars = offer.offeredValueCents / 100>
                                <p>Offered Amount: $${offeredAmountDollars?string("0.00")}</p>
                                <#if offer.counterOfferCents??>
                                    <#assign counterAmountDollars = offer.counterOfferCents / 100>
                                    <p>Counter Offer Amount: $${counterAmountDollars?string("0.00")}</p>
                                </#if>
                                <p>Status: ${offer.status}</p>
                                <div class="mt-3">
                                <#if offer.status == "ACCEPTED">
                                    <form action="/providers/trade-offers/${offer.id}/complete" method="post" class="d-inline">
                                        <button type="submit" class="btn btn-primary">Mark as Complete</button>
                                    </form>
                                <#else>
                                    <form action="/providers/trade-offers/${offer.id}/accept" method="post" class="d-inline">
                                        <button type="submit" class="btn btn-success">Accept</button>
                                    </form>
                                    <form action="/providers/trade-offers/${offer.id}/reject" method="post" class="d-inline">
                                        <button type="submit" class="btn btn-danger">Reject</button>
                                    </form>
                                    <form action="/providers/trade-offers/${offer.id}/counter" method="post" class="d-inline">
                                        <input type="number" step="0.01" name="counterAmount" placeholder="Counter Amount" required>
                                        <button type="submit" class="btn btn-warning">Counter</button>
                                    </form>
                                </#if>
                            </div>
                            <#else>
                                <h5>Trade Offer from ${offer.buyer.username} for Listing: <a href="/listings/my-listings/${offer.listing.id}">${offer.listing.card.name} (ID: ${offer.listing.id})</a></h5>
                                <p>Offered Card: ${offer.offeredCardName} (${offer.offeredCardGame} - ${offer.offeredCardSet}, Rarity: ${offer.offeredCardRarity}, Grade: ${offer.offeredCardGrade}, Condition: ${offer.offeredCondition})</p>
                                <p>Status: ${offer.status}</p>
                                <div class="mt-3">
                                <#if offer.status == "ACCEPTED">
                                    <form action="/providers/trade-offers/${offer.id}/complete" method="post" class="d-inline">
                                        <button type="submit" class="btn btn-primary">Mark as Complete</button>
                                    </form>
                                <#else>
                                    <form action="/providers/trade-offers/${offer.id}/accept" method="post" class="d-inline">
                                        <button type="submit" class="btn btn-success">Accept</button>
                                    </form>
                                    <form action="/providers/trade-offers/${offer.id}/reject" method="post" class="d-inline">
                                        <button type="submit" class="btn btn-danger">Reject</button>
                                    </form>
                                </#if>
                            </div>
                            </#if>
                        </div>
                    </#if>
                </#list>
            </#if>
        </div>
    </div>
    <!--Bootstrap JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/provider-logout.js"></script>
</body>