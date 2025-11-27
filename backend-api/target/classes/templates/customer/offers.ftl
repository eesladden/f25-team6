<#macro renderContent>
  <h2>My Offers</h2>

  <form method="post" action="/customer/offers/create">
    <label>Listing ID: <input name="listingId" required></label>
    <label>Offer (cents): <input name="amountCents" type="number" min="1" required></label>
    <button type="submit">Create Offer</button>
  </form>

  <table>
    <thead>
      <tr>
        <th>Offer ID</th>
        <th>Listing</th>
        <th>Amount</th>
        <th>Status</th>
        <th>Created</th>
      </tr>
    </thead>
    <tbody>
      <#list offers as o>
        <tr>
          <td>${o.id}</td>
          <td>
            #${(o.listing.id)!} · ${(o.listing.tradingFor)!'Card name not set'}
            (${(o.listing.condition)!''} ${(o.listing.grade)!''})
          </td>
          <td>$${(o.amountCents/100)?string["0.00"]}</td>
          <td>${o.status}</td>
          <td>${(o.createdAt)!''}</td>
        </tr>
      </#list>
    </tbody>
  </table>
</#macro>
<#include "layout.ftl">
