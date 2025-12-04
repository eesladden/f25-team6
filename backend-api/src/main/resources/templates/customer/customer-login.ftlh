<#-- File: templates/customer/customer-login.ftlh -->
<#assign pageTitle = "Customer Login" />

<#macro renderContent>
    <div class="container mt-4" style="max-width: 500px;">
        <h1 class="mb-3 text-center">Customer Login</h1>
        <p class="text-center text-muted">
            Log in with your email, phone, or username and password.
        </p>

        <#if loginError?? && loginError>
            <div class="alert alert-danger">
                Invalid credentials. Please check your email/phone/username and password.
            </div>
        </#if>

        <form method="post"
              action="/customers/login"
              class="card p-3 shadow-sm">

            <div class="mb-3">
                <label for="usernameOrEmail" class="form-label">
                    Email, Phone, or Username
                </label>
                <input type="text"
                       class="form-control"
                       id="usernameOrEmail"
                       name="usernameOrEmail"
                       required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password"
                       class="form-control"
                       id="password"
                       name="password"
                       required>
            </div>

            <button type="submit" class="btn btn-primary w-100">
                Log In
            </button>
        </form>

        <div class="text-center mt-3">
            <span class="text-muted">New to TradeTable?</span>
            <a href="/customers/signup">Create an account</a>
        </div>
    </div>
</#macro>

<#include "layout.ftlh">
