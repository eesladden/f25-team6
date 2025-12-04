<#-- File: templates/customer/customer-signup.ftlh -->
<#assign pageTitle = "Customer Sign Up" />

<#macro renderContent>
    <div class="container mt-4" style="max-width: 550px;">
        <h1 class="mb-3 text-center">Create Your TradeTable Account</h1>
        <p class="text-center text-muted">
            Sign up to start managing your wishlist, offers, and trades.
        </p>

        <#-- Password mismatch error -->
        <#if passwordMismatchError?? && passwordMismatchError>
            <div class="alert alert-danger">
                Passwords do not match. Please re-enter them.
            </div>
        </#if>

        <#-- Generic signup error -->
        <#if signupError??>
            <div class="alert alert-danger">
                ${signupError}
            </div>
        </#if>

        <form method="post" action="/customers/signup" class="card p-3 shadow-sm">

            <div class="mb-3">
                <label for="displayName" class="form-label">Display Name (optional)</label>
                <input type="text"
                       class="form-control"
                       id="displayName"
                       name="displayName"
                       placeholder="What other traders will see">
            </div>

            <div class="mb-3">
                <label for="profileImageUrl" class="form-label">Profile Image URL (optional)</label>
                <input type="url"
                       class="form-control"
                       id="profileImageUrl"
                       name="profileImageUrl"
                       placeholder="Paste a link to your avatar image (Imgur, TCGPlayer, etc.)">
            </div>

            <div class="mb-3">
                <label for="username" class="form-label">Username *</label>
                <input type="text"
                       class="form-control"
                       id="username"
                       name="username"
                       required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email *</label>
                <input type="email"
                       class="form-control"
                       id="email"
                       name="email"
                       required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password *</label>
                <input type="password"
                       class="form-control"
                       id="password"
                       name="password"
                       required>
            </div>

            <div class="mb-3">
                <label for="confirmPassword" class="form-label">Confirm Password *</label>
                <input type="password"
                       class="form-control"
                       id="confirmPassword"
                       name="confirmPassword"
                       required>
            </div>

            <div class="mb-3">
                <label for="phoneNumber" class="form-label">Phone Number *</label>
                <input type="tel"
                        class="form-control"
                        id="phoneNumber"
                        name="phoneNumber"
                        required>
            </div>


            <button type="submit" class="btn btn-success w-100">
                Create Account
            </button>
        </form>

        <div class="text-center mt-3">
            <span class="text-muted">Already have an account?</span>
            <a href="/customers/login">Log in</a>
        </div>
    </div>
</#macro>

<#include "layout.ftlh">
