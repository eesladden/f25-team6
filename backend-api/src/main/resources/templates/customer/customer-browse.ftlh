<#-- File: templates/customer/customer-browse.ftlh -->
<#assign pageTitle = "Browse Listings" />

<#macro renderContent>
    <div class="container mt-4">
        <h1 class="mb-4">Browse Listings</h1>

        <#-- If there are no listings -->
        <#if !listings?? || (listings?size == 0)>
            <p class="text-muted">
                There are no available listings at this time.
            </p>
        <#else>
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <#list listings as listing>
                    <div class="col mb-4">
                        <div class="card h-100 shadow-sm">
                            <#-- Card image -->
                            <img
                                src="/card-images/${(listing.card.imagePath)!'default-listing.png'}"
                                class="card-img-top"
                                alt="${listing.card.name!''}">

                            <div class="card-body">
                                <h5 class="card-title mb-1">
                                    ${listing.card.name!"(No name)"}
                                </h5>

                                <p class="card-text mb-1">
                                    <strong>Game:</strong>
                                    ${listing.card.game!"-"}
                                </p>

                                <p class="card-text mb-1">
                                    <strong>Set:</strong>
                                    ${listing.card.set!"-"}
                                </p>

                                <p class="card-text mb-1">
                                    <strong>Rarity:</strong>
                                    ${listing.card.rarity!"-"}
                                </p>

                                <p class="card-text mb-1">
                                    <strong>Condition:</strong>
                                    ${listing.condition!"-"}
                                </p>

                                <p class="card-text mb-1">
                                    <strong>For:</strong>
                                    ${listing.forSaleOrTrade!"-"}
                                </p>

                                <p class="card-text mb-1">
                                    <strong>Provider:</strong>
                                    <a href="/listings/provider/${listing.provider.username}">
                                        ${listing.provider.username}
                                    </a>
                                </p>
                            </div>

                            <div class="card-footer bg-white border-top-0">
                                <a href="/customer/listing/${listing.id}"
                                   class="btn btn-outline-primary w-100">
                                    View Listing
                                </a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </#if>
    </div>
</#macro>

<#include "layout.ftlh">
