<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Change Password</title>
    <link rel="stylesheet" type="text/css" href="/css/provider-style.css">
</head>
<body style="background-image: url(&quot;/provider-images/${(provider.bgImagePath)!'tcg-bg.jpg'}&quot;); background-size: cover; background-position: center; background-repeat: no-repeat; background-attachment: fixed; background-color: rgba(255, 255, 255, 0.8); background-blend-mode: lighten;">
    <!---NAVBAR Start-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/providers/dashboard">TradeTable</a>
            <form id="logoutForm" action="/providers/logout" class="position-absolute end-0 me-5 pe-2" method="post">
                <button type="submit" class="btn btn-outline-danger">Logout</button>
            </form>
            <a class="nav-link" href="/providers/profile">
                <img src="/profile-pictures/${(provider.profileImagePath)!'default-profile.png'}" alt="Profile" width="30" height="30" class="d-inline-block align-top">
            </a>
        </div>
    </nav>
    <!---NAVBAR End-->
    <!--Breadcrumbs Start-->
    <nav aria-label="breadcrumb" class="mt-4 ms-5">
        <ol class="breadcrumb ms-3">
            <li class="breadcrumb-item"><a href="/providers/profile">Profile</a></li>
            <li class="breadcrumb-item active" aria-current="page">Change Password</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!---Change Password Form-->
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
        <div class="border p-4" style="max-width: 500px; width: 100%;">
        <h2 class="text-center">Change Password</h2>
        <form action="/providers/profile/change-password" method="post" novalidate>
            <div class="mb-3">
                <label for="currentPassword" class="form-label">Current Password</label>
                <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                <#if changePasswordError?? && changePasswordError>
                    <div class="text-danger mt-2">Current password is incorrect.</div>
                </#if>
            </div>
            <div class="mb-3">
                <label for="newPassword" class="form-label">New Password</label>
                <input type="password" class="form-control" id="newPassword" name="newPassword" required>
            </div>
            <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary" style="flex: 1.3;">Update Password</button>
                <a class="btn btn-secondary flex-fill" href="/providers/profile" style="flex: 0.4;">Cancel</a>
            </div>
        </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/provider-logout.js"></script>
</body>
</html>