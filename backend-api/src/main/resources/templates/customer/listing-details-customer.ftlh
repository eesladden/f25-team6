<#-- File: templates/customer/listing-details-customer.ftlh -->
<#assign pageTitle = "Listing Details" />

<#macro renderContent>
    <container class="mt-4 mb-5">
    <#if listing.forSaleOrTrade == "For Sale">
    <!--For Sale Listing Details-->
        <div class="containerSale py-4 pe-3 border rounded mx-auto mt-5 w-100 d-flex bg-white bg-opacity-100" style="max-width: 900px;">
            <!-- Image -->
            <div class="col-md-5 ps-3">
                <img src="/card-images/${listing.card.imagePath!'image-not-found.png'}" class="card-img-top" style="width: 90%;" alt="${listing.card.name}">
            </div>

            <!-- Details -->
            <div class="col-md-7">
            <h1 class="h4 mb-1">${listing.card.name} — ${listing.condition}</h1>
            <p class="text-secondary small mb-3">${listing.card.game} • ${listing.card.set} • Listing ID #${listing.id}</p>
            <!-- Estimated value box -->
            <div class="value-box mb-3">
                <div class="d-flex justify-content-between small">
                <span>Market</span><strong>$${listing.marketPrice}</strong>
                </div>
                <div class="d-flex justify-content-between small">
                <span>Low / High</span><strong>$${listing.lowPrice} / $${listing.highPrice}</strong>
                </div>
            </div>
            
            <!--Make Offer Button-->
            <div class="d-flex gap-2 mb-3">
                <a href="/customer/offers" class="btn btn-primary">Make Offer</a>
            </div>

            <hr>
            <h2 class="h6">Details</h2>
            <ul class="small">
                <li>Location: ${listing.location}</li>
                <li>Game: ${listing.card.game}</li>
                <li>Set: ${listing.card.set}</li>
                <li>Rarity: ${listing.card.rarity}</li>
                <li>Condition: ${listing.condition}</li>
                <li>Grading: ${listing.grade}</li>
                <li>Seller: ${listing.provider.name}</li>
            </ul>
            </div>
        </div>
        <#else>
    <!--For Trade Listing Details-->
        <div class="containerTrade py-4 pe-3 border rounded mx-auto mt-5 w-100 d-flex bg-white bg-opacity-100" style="max-width: 900px;">
            <!-- Image -->
            <div class="col-md-5 ps-3">
                <img src="/card-images/${listing.card.imagePath!'image-not-found.png'}" class="card-img-top" style="width: 90%;" alt="${listing.card.name}">
            </div>
        <!-- Details -->
            <div class="col-md-7">
            <h1 class="h4 mb-1">${listing.card.name} — ${listing.condition}</h1>
            <p class="text-secondary small mb-3">${listing.card.game} • ${listing.card.set} • Listing ID #${listing.id}</p>
            <!-- Estimated value box -->
            <div class="value-box mb-3">
                <div class="d-flex justify-content-between small">
                <span>Trading For:</span><strong>${listing.tradingFor}</strong>
                </div>
            </div>

            <!--Propose Trade Button-->
            <div class="d-flex gap-2 mb-3">
                <a href="/customer/offers" class="btn btn-primary">Propose Trade</a>
            </div>

            <hr>
            <h2 class="h6">Details</h2>
            <ul class="small">
                <li>Game: ${listing.card.game}</li>
                <li>Set: ${listing.card.set}</li>
                <li>Rarity: ${listing.card.rarity}</li>
                <li>Condition: ${listing.condition}</li>
                <li>Grading: ${listing.grade}</li>
                <li>Seller: ${listing.provider.name}</li>
            </ul>
            </div>
        </div>
        </#if>
    </container>
</#macro>

<#include "layout.ftlh">