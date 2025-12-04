<#-- File: templates/customer/customer-profile.ftlh -->
<#assign pageTitle = "My Profile" />

<#macro renderContent>
    <div class="container mt-4">
        <h1 class="mb-3">My Profile</h1>

        <div class="card mb-4 shadow-sm">
            <div class="card-body">

                <div class="d-flex align-items-center mb-3">
                    <img
                        src="${me.profileImageUrl!'https://via.placeholder.com/96?text=TT'}"
                        alt="Profile picture"
                        class="rounded-circle me-3"
                        style="width: 96px; height: 96px; object-fit: cover;">
                    <div>
                        <h4 class="card-title mb-1">
                            ${me.displayName!me.username!"Unnamed Trader"}
                        </h4>
                        <div class="text-muted">
                            @${me.username!"(no username)"}
                        </div>
                    </div>
                </div>

                <dl class="row mb-0">
                    <dt class="col-sm-3">Email</dt>
                    <dd class="col-sm-9">${me.email!"-"}</dd>

                    <dt class="col-sm-3">Preferred Sets</dt>
                    <dd class="col-sm-9">${me.preferredSets!"Not specified"}</dd>

                    <dt class="col-sm-3">Region</dt>
                    <dd class="col-sm-9">${me.shippingRegion!"Not specified"}</dd>

                    <dt class="col-sm-3">Bio</dt>
                    <dd class="col-sm-9">
                        <#if me.bio?? && me.bio?length > 0>
                            ${me.bio}
                        <#else>
                            <span class="text-muted">Tell other traders about your interests.</span>
                        </#if>
                    </dd>
                </dl>
            </div>
        </div>

        <div class="d-flex flex-wrap gap-2">
            <a class="btn btn-primary" href="/customer/profile/edit">
                Edit Profile
            </a>
            <a class="btn btn-outline-secondary" href="/customer/profile/change-password">
                Change Password
            </a>

            <form method="post"
                  action="/customer/profile/delete"
                  onsubmit="return confirm('Are you sure you want to delete your account? This cannot be undone.');">
                <button type="submit" class="btn btn-outline-danger">
                    Delete Account
                </button>
            </form>
        </div>
    </div>
</#macro>

<#include "layout.ftlh">
