<#-- File: templates/customer/customer-change-password.ftlh -->
<#assign pageTitle = "Change Password" />

<#macro renderContent>
    <div class="container mt-4" style="max-width: 500px;">
        <h1 class="mb-3">Change Password</h1>

        <#if changePasswordError?? && changePasswordError>
            <div class="alert alert-danger">
                Your current password is incorrect. Please try again.
            </div>
        </#if>

        <form method="post"
              action="/customer/profile/change-password"
              class="card p-3 shadow-sm">

            <div class="mb-3">
                <label for="currentPassword" class="form-label">Current Password</label>
                <input type="password"
                       class="form-control"
                       id="currentPassword"
                       name="currentPassword"
                       required>
            </div>

            <div class="mb-3">
                <label for="newPassword" class="form-label">New Password</label>
                <input type="password"
                       class="form-control"
                       id="newPassword"
                       name="newPassword"
                       required>
            </div>

            <div class="d-flex justify-content-between">
                <a href="/customer/profile" class="btn btn-outline-secondary">
                    Cancel
                </a>
                <button type="submit" class="btn btn-primary">
                    Update Password
                </button>
            </div>
        </form>
    </div>
</#macro>

<#include "layout.ftlh">
