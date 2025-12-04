<#macro renderContent>
  <h2 class="mb-3">Customer Dashboard</h2>
  <p class="text-muted mb-4">
    Welcome, <strong>${me.displayName!"Customer"}</strong>!
  </p>

  <div class="row g-3 mb-4">
    <div class="col-md-6">
      <div class="card border-0 shadow-sm h-100">
        <div class="card-body">
          <h5 class="card-title">Wishlist</h5>
          <p class="card-text">
            You currently have <strong>${wishCount!0}</strong> item(s) in your wishlist.
          </p>
          <a href="/customer/wishlist" class="btn btn-primary">View Wishlist</a>
        </div>
      </div>
    </div>

    <div class="col-md-6">
      <div class="card border-0 shadow-sm h-100">
        <div class="card-body">
          <h5 class="card-title">My Offers</h5>
          <p class="card-text">
            You have made <strong>${offerCount!0}</strong> offer(s).
          </p>
          <a href="/customer/offers" class="btn btn-secondary">View Offers</a>
        </div>
      </div>
    </div>
  </div>

  <div class="row g-3 mb-4">
    <div class="col-md-6">
      <div class="card border-0 shadow-sm h-100">
        <div class="card-body">
          <h5 class="card-title">Messages</h5>
          <p class="card-text">
            View and continue your conversations with providers.
          </p>
          <a href="/customer/messages/conversations/my-conversations"
             class="btn btn-outline-primary">
            Open Messages
          </a>
        </div>
      </div>
    </div>

    <div class="col-md-6">
      <div class="card border-0 shadow-sm h-100">
        <div class="card-body">
          <h5 class="card-title">My Reviews</h5>
          <p class="card-text">
            See reviews you’ve written for providers.
          </p>
          <a href="/customer/reviews/my-reviews"
             class="btn btn-outline-secondary">
            View My Reviews
          </a>
        </div>
      </div>
    </div>
  </div>

  <hr>

  <p class="mb-0">
    Ready to find new cards?
    <a href="/customer/browse">Browse listings</a>
  </p>
</#macro>
<#include "layout.ftlh">
