<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Provider Profile</title>
    <link rel="stylesheet" type="text/css" href="/css/provider-style.css">
</head>
<body>
    <!---NAVBAR Start-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/providers/dashboard">TradeTable</a>
            <a class="listingNav nav-link align-items-start" style="margin-left: -1200px;" href="/listings">Listings</a>
            <form id="logoutForm" action="/providers/logout" method="post" class="logoutFormNS d-inline">
                <button type="submit" class="logoutButton btn btn-outline-danger">Logout</button>
            </form>
            <a class="nav-link" href="/providers/profile">
                <img src="/profile-pictures/${(provider.profileImagePath)!'default-profile.png'}" alt="Profile" width="30" height="30" class="d-inline-block align-top">
            </a>
        </div>
    </nav>
    <!---NAVBAR End-->
    <!---Profile Content-->
    <div class="container mt-4" style="margin-top: 20px;">
        <div class="row justify-content-center align-items-center" style="min-height: 500px;">
            <div class="col-md-4">
                <div class="text-center border p-4 rounded" style="background-color: #f8f9fa;">
                    <img src="/profile-pictures/${(provider.profileImagePath)!'default-profile.png'}" alt="Profile Picture" class="rounded-circle" width="150" height="150">
                    <h2 class="mt-3">${provider.username}</h2>
                    <p class="lead">${(provider.bio)!"No bio available."}</p>
                    <div class="row mb-2 g-2 ms-3 me-3">
                        <div class="col">
                            <a href="/providers/dashboard" class="btn btn-primary w-100">View Dashboard</a>
                        </div>
                        <div class="col">
                            <a href="/providers/profile/change-password" class="btn btn-secondary w-100">Change Password</a>
                        </div>
                    </div>
                    <div class="row mb-3 g-2 ms-3 me-3">
                        <div class="col">
                            <a href="/providers/profile/edit" class="btn btn-outline-secondary w-100">Edit Profile</a>
                        </div>
                        <div class="col">
                            <form id="deleteAccountForm" action="/providers/delete-account" method="post" onsubmit="return confirm('Are you sure you want to delete your account? This action cannot be undone.');">
                                <button type="submit" class="btn btn-danger w-100">Delete Account</button>
                            </form>
                        </div>
                    </div>
                    <!--Rating Stars (static for now)-->
                    <div class="mb-3">
                        <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
                        <span class="ms-2">4.0 out of 5</span>
                    </div>
                    <p class="text-muted mt-3">Member since: ${provider.yearCreated}</p>
                    <p class="text-muted"># of Cards Listed: ${provider.listingsListed}</p>
                    <p class="text-muted"># of Trades Completed: ${provider.tradesCompleted}</p>
                    <p class="text-muted"># of Cards Collected: ${provider.collectionSize}</p>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/provider-logout.js"></script>
</body>
</html>