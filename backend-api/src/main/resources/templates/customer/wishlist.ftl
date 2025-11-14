<#macro renderContent>
  <h2>My Wishlist</h2>

  <form method="post" action="/customer/wishlist/add">
    <label>Listing ID: <input name="listingId" required></label>
    <button type="submit">Add</button>
  </form>

  <table>
    <thead>
      <tr>
        <th>Wishlist ID</th>
        <th>Listing</th>
        <th>Location</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <#list items as it>
        <tr>
          <td>${it.id}</td>
          <td>
            #${(it.listing.id)!} · ${(it.listing.tradingFor)!'Card name not set'}
            (${(it.listing.condition)!''} ${(it.listing.grade)!''})
          </td>
          <td>${(it.listing.cityName)!''}, ${(it.listing.stateName)!''}</td>
          <td>
            <form method="post" action="/customer/wishlist/remove">
              <input type="hidden" name="itemId" value="${it.id}">
              <button>Remove</button>
            </form>
          </td>
        </tr>
      </#list>
    </tbody>
  </table>
</#macro>
<#include "layout.ftl">
