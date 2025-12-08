<#-- File: templates/customer/customer-review-new.ftlh -->
<#assign pageTitle = "Write a Review" />

<#macro renderContent>
    <div class="container mt-4">
        <h1 class="mb-3">Write a Review</h1>

        <p class="text-muted mb-3">
            Reviewing provider
            <strong>${provider.username}</strong>
            <#if listing??>
                for listing
                <strong>${listing.card.name}</strong>
                (${listing.card.game} - ${listing.card.set})
            </#if>
        </p>

        <form action="/customer/reviews" method="post">
            <input type="hidden" name="providerId" value="${provider.id}">
            <#if listing??>
                <input type="hidden" name="listingId" value="${listing.id}">
            </#if>

            <div class="mb-3">
                <label for="rating" class="form-label">Rating (1–5)</label>
                <select id="rating" name="rating" class="form-select" required>
                    <option value="">Select rating</option>
                    <#list 1..5 as r>
                        <option value="${r}">${r}</option>
                    </#list>
                </select>
            </div>

            <div class="mb-3">
                <label for="comment" class="form-label">Comment</label>
                <textarea id="comment"
                          name="comment"
                          class="form-control"
                          rows="4"
                          required></textarea>
            </div>

            <div class="mb-3">
                <label class="form-label">Tags (optional)</label>
                <div class="d-flex flex-wrap gap-2">
                    <#list ["FRIENDLY","PUNCTUAL","PROFESSIONAL","HIGH_QUALITY","RECOMMENDED",
                            "RESPONSIVE","FLEXIBLE","KNOWLEDGEABLE","HELPFUL","COURTEOUS"] as tag>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input"
                                   type="checkbox"
                                   id="tag-${tag}"
                                   name="tags"
                                   value="${tag}">
                            <label class="form-check-label" for="tag-${tag}">
                                ${tag}
                            </label>
                        </div>
                    </#list>
                </div>
            </div>

            <button type="submit" class="btn btn-success">Submit Review</button>
            <a href="/customer/reviews/my-reviews" class="btn btn-secondary ms-2">Cancel</a>
        </form>
    </div>
</#macro>

<#include "layout.ftlh">
