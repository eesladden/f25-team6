<#macro renderContent>
  <h2 class="mb-4">My Wishlist</h2>

  <!-- Add to Wishlist Form -->
  <div class="card border-0 shadow-sm mb-4">
    <div class="card-body">
      <h5 class="card-title mb-3">Add Listing to Wishlist</h5>
      <form class="row g-3" method="post" action="/customer/wishlist/add">
        <div class="col-md-6">
          <label class="form-label">Listing ID</label>
          <input name="listingId" class="form-control" required>
        </div>
        <div class="col-12 d-grid d-md-flex justify-content-md-end">
          <button type="submit" class="btn btn-primary mt-2">Add</button>
        </div>
      </form>
    </div>
  </div>

  <!-- Wishlist Table -->
  <div class="card border-0 shadow-sm">
    <div class="card-body">
      <h5 class="card-title mb-3">Saved Listings</h5>

      <#if items?size == 0>
        <p class="text-muted mb-0">Your wishlist is empty.</p>
      <#else>
        <div class="table-responsive">
          <table class="table table-striped align-middle mb-0">
            <thead>
              <tr>
                <th scope="col">Wishlist ID</th>
                <th scope="col">Listing</th>
                <th scope="col">Location</th>
                <th scope="col" style="width: 120px;"></th>
              </tr>
            </thead>
            <tbody>
              <#list items as it>
                <tr>
                  <td>${it.id}</td>
                  <td>
                    <#if it.listing??>
                      #${(it.listing.id)!} · ${(it.listing.card.name)!'Card name not set'}
                      <br>
                      <small class="text-muted">
                        ${(it.listing.condition)!''} ${(it.listing.grade)!''}
                      </small>
                    <#else>
                      <span class="text-muted">Listing no longer available</span>
                    </#if>
                  </td>
                  <td>
                    <#if it.listing??>
                      ${(it.listing.location)!''}
                    <#else>
                      <span class="text-muted">N/A</span>
                    </#if>
                  </td>
                  <td>
                    <form method="post" action="/customer/wishlist/remove" class="d-inline">
                      <input type="hidden" name="itemId" value="${it.id}">
                      <button class="btn btn-sm btn-outline-danger">Remove</button>
                    </form>
                  </td>
                </tr>
              </#list>
            </tbody>
          </table>
        </div>
      </#if>
    </div>
  </div>
</#macro>
<#include "layout.ftlh">
