<#-- File: templates/customer/customer-offers.ftlh -->
<#assign pageTitle = "My Offers" />

<#macro renderContent>
    <div class="container mt-4">
        <h1 class="mb-3">My Offers</h1>

        <#-- Flash messages -->
        <#if offerMessage??>
            <div class="alert alert-success">
                ${offerMessage}
            </div>
        </#if>
        <#if offerError??>
            <div class="alert alert-danger">
                ${offerError}
            </div>
        </#if>

        <div class="card mb-4 shadow-sm">
            <div class="card-body">
                <h5 class="card-title mb-3">Create New Offer</h5>
                <form method="post"
                      action="/customer/offers/create"
                      class="row g-3 align-items-end">

                    <div class="col-md-4">
                        <label for="listingId" class="form-label">Listing ID</label>
                        <input type="number"
                               class="form-control"
                               id="listingId"
                               name="listingId"
                               min="1"
                               required>
                        <div class="form-text">
                            Enter the listing ID you want to make an offer on.
                        </div>
                    </div>

                    <div class="col-md-4">
                        <label for="amountCents" class="form-label">Offer Amount (in cents)</label>
                        <input type="number"
                               class="form-control"
                               id="amountCents"
                               name="amountCents"
                               min="1"
                               required>
                        <div class="form-text">
                            Example: 1500 = $15.00
                        </div>
                    </div>

                    <div class="col-md-4">
                        <button type="submit" class="btn btn-success w-100">
                            Submit Offer
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <#-- Offers table -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title mb-3">Your Offers</h5>

                <#if offers?? && (offers?size > 0)>
                    <div class="table-responsive">
                        <table class="table table-striped align-middle mb-0">
                            <thead>
                            <tr>
                                <th>Offer ID</th>
                                <th>Listing</th>
                                <th>Game</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Counter</th>
                                <th></th>
                                <th>Created</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list offers as offer>
                                <tr>
                                    <td>${offer.id}</td>

                                    <td>
                                        <#if offer.listing??>
                                            <#if offer.listing.card??>
                                                ${offer.listing.card.name!"(no title)"}
                                                <br>
                                                <small class="text-muted">
                                                    ${offer.listing.card.set!"-"}
                                                </small>
                                            <#else>
                                                ${offer.listing.title!"(no title)"}
                                            </#if>
                                        <#else>
                                            -
                                        </#if>
                                    </td>

                                    <td>
                                        <#if offer.listing??>
                                            <#if offer.listing.card??>
                                                ${offer.listing.card.game!"-"}
                                            <#else>
                                                ${offer.listing.game!"-"}
                                            </#if>
                                        <#else>
                                            -
                                        </#if>
                                    </td>

                                    <td>
                                        <#if offer.amountCents??>
                                            $${(offer.amountCents / 100.0)?string["0.00"]}
                                        <#else>
                                            -
                                        </#if>
                                    </td>

                                    <td>
                                        <#if offer.status??>
                                            <span class="badge
                                                <#if offer.status == "PENDING">bg-warning text-dark
                                                <#elseif offer.status == "ACCEPTED">bg-success
                                                <#elseif offer.status == "REJECTED">bg-danger
                                                <#elseif offer.status == "WITHDRAWN">bg-secondary
                                                <#elseif offer.status == "COMPLETED">bg-info text-dark
                                                <#else>bg-light text-dark
                                                </#if>">
                                                ${offer.status}
                                            </span>
                                        <#else>
                                            -
                                        </#if>
                                    </td>

                                    <#if offer.status == "COUNTERED">
                                        <td>
                                            <#if offer.counterOfferCents??>
                                                $${(offer.counterOfferCents / 100.0)?string["0.00"]}
                                            <#else>
                                                -
                                            </#if>
                                        </td>
                                    <#else>
                                        <td>-</td>
                                    </#if>

                                    <td>
                                        <#if offer.createdAt??>
                                            <td>${offer.createdAt!}</td>

                                        <#else>
                                            -
                                        </#if>
                                    </td>

                                    <td>
                                        <div class="d-flex flex-column gap-1">

                                            <#-- Withdraw button for pending offers -->
                                            <#if offer.status == "PENDING">
                                                <form method="post"
                                                      action="/customer/offers/${offer.id}/withdraw"
                                                      style="display:inline;">
                                                    <button type="submit"
                                                            class="btn btn-sm btn-outline-warning w-100">
                                                        Withdraw
                                                    </button>
                                                </form>
                                            </#if>

                                            <#if offer.status == "COUNTERED">
                                                <form method="post"
                                                      action="/customer/offers/${offer.id}/accept-counter"
                                                      style="display:inline;">
                                                    <button type="submit"
                                                            class="btn btn-sm btn-outline-success w-100">
                                                        Accept Counter
                                                    </button>
                                                </form>
                                                <form method="post"
                                                      action="/customer/offers/${offer.id}/reject-counter"
                                                      style="display:inline;">
                                                    <button type="submit"
                                                            class="btn btn-sm btn-outline-danger w-100">
                                                        Reject Counter
                                                    </button>
                                                </form>
                                            </#if>

                                            <#-- Message provider -->
                                            <#if offer.listing??>
                                                <form method="post"
                                                      action="/customer/messages/conversations/start"
                                                      style="display:inline;">
                                                    <input type="hidden"
                                                           name="listingId"
                                                           value="${offer.listing.id}">
                                                    <input type="hidden"
                                                           name="content"
                                                           value="Hi, I'm interested in your listing.">
                                                    <button type="submit"
                                                            class="btn btn-sm btn-outline-primary w-100">
                                                        Message Provider
                                                    </button>
                                                </form>

                                                <#-- Write review (if completed) -->
                                                <#if offer.status == "COMPLETED" && offer.listing.provider??>
                                                    <a href="/customer/reviews/new?providerId=${offer.listing.provider.id}&listingId=${offer.listing.id}"
                                                       class="btn btn-sm btn-outline-success w-100">
                                                        Write Review
                                                    </a>
                                                </#if>
                                            </#if>

                                        </div>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                <#else>
                    <p class="text-muted mb-0">
                        You haven’t made any offers yet. Use the form above to create one.
                    </p>
                </#if>
            </div>
        </div>
    </div>
</#macro>

<#include "layout.ftlh">
