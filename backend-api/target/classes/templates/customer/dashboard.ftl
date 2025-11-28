<#macro renderContent>
  <h1>Welcome, ${me.displayName!"Customer"}</h1>
  <p>Wishlist items: <b>${wishCount!0}</b></p>
  <p>My offers: <b>${offerCount!0}</b></p>
  <p><a href="/customer/wishlist">Go to Wishlist</a> · <a href="/customer/offers">Go to Offers</a></p>
</#macro>
<#include "layout.ftl">
