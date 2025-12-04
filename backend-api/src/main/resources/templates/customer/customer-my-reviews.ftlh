<#-- File: templates/customer/customer-my-reviews.ftlh -->
<#assign pageTitle = "My Reviews" />

<#macro renderContent>
    <div class="container mt-4">
        <h1 class="mb-4">My Reviews</h1>

        <#if reviews?? && (reviews?size > 0)>
            <div class="list-group">
                <#list reviews as review>
                    <div class="list-group-item mb-3 rounded">
                        <div class="d-flex justify-content-between">
                            <div>
                                <strong>Rating:</strong>
                                <#list 1..5 as star>
                                    <#if star <= review.rating>
                                        <span class="text-warning">&#9733;</span>
                                    <#else>
                                        <span class="text-secondary">&#9734;</span>
                                    </#if>
                                </#list>
                                <span class="ms-1 align-middle fw-bold"
                                      style="color: black; font-family: Arial, sans-serif;">
                                    ${review.rating} / 5
                                </span>
                            </div>
                            <div>
                                <small class="text-muted">
                                    Provider: ${review.provider.username}
                                    <#if review.listing?? && review.listing.card??>
                                        &nbsp;|&nbsp;
                                        Listing: ${review.listing.card.name}
                                    </#if>
                                </small>
                            </div>
                        </div>

                        <p class="mb-1 mt-2">${review.comment}</p>
                        <small class="text-muted">
                            Date: ${review.createdAtString}
                        </small>

                        <#-- Tags: if tags is a list/collection -->
                        <#if review.tags?? && review.tags?has_content>
                            <div class="mt-2">
                                <strong>Tags:</strong>
                                <#list review.tags as tag>
                                    <span class="badge bg-info text-dark me-1">${tag}</span>
                                </#list>
                            </div>
                        </#if>

                        <#if review.providerResponse??>
                            <div class="mt-3 mb-2 p-3 bg-light border rounded">
                                <h6>Provider Response:</h6>
                                <p>${review.providerResponse}</p>
                                <small class="text-muted">
                                    Date: ${review.responseAtString}
                                </small>
                            </div>
                        </#if>
                    </div>
                </#list>
            </div>
        <#else>
            <p class="text-muted">You have not written any reviews yet.</p>
        </#if>
    </div>
</#macro>

<#include "layout.ftlh">
