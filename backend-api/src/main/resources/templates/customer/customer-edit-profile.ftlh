<#-- File: templates/customer/customer-edit-profile.ftlh -->
<#assign pageTitle = "Edit Profile" />

<#macro renderContent>
    <div class="container mt-4" style="max-width: 700px;">
        <h1 class="mb-3">Edit Profile</h1>

        <form method="post" action="/customer/profile/edit" class="card p-3 shadow-sm">
            <div class="mb-3">
                <label for="displayName" class="form-label">Display Name</label>
                <input type="text"
                       class="form-control"
                       id="displayName"
                       name="displayName"
                       value="${me.displayName!}">
            </div>

            <div class="mb-3">
                <label for="profileImageUrl" class="form-label">Profile Image URL</label>
                <input type="url"
                       class="form-control"
                       id="profileImageUrl"
                       name="profileImageUrl"
                       placeholder="Paste a link to your avatar image"
                       value="${me.profileImageUrl!}">
                <div class="form-text">
                    You can use an image hosted on Imgur, TCGPlayer, etc.
                </div>
            </div>

            <div class="mb-3">
                <label for="preferredSets" class="form-label">Preferred Sets</label>
                <input type="text"
                       class="form-control"
                       id="preferredSets"
                       name="preferredSets"
                       placeholder="e.g., One Piece OP-05, Pokémon Evolving Skies"
                       value="${me.preferredSets!}">
            </div>

            <div class="mb-3">
                <label for="shippingRegion" class="form-label">Shipping Region</label>
                <input type="text"
                       class="form-control"
                       id="shippingRegion"
                       name="shippingRegion"
                       placeholder="e.g., US Only, North America, Worldwide"
                       value="${me.shippingRegion!}">
            </div>

            <div class="mb-3">
                <label for="bio" class="form-label">Bio</label>
                <textarea class="form-control"
                          id="bio"
                          name="bio"
                          rows="4"
                          placeholder="Tell others what you collect, what you want, and how you like to trade.">${me.bio!}</textarea>
            </div>

            <div class="d-flex justify-content-between">
                <a href="/customer/profile" class="btn btn-outline-secondary">
                    Cancel
                </a>
                <button type="submit" class="btn btn-primary">
                    Save Changes
                </button>
            </div>
        </form>
    </div>
</#macro>

<#include "layout.ftlh">
