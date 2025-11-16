<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Edit Provider Profile</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <!---NAVBAR Start-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/providers/dashboard">TradeTable</a>
            <a class="listingNav nav-link align-items-start" style="margin-left: -1200px;" href="/listings">Listings</a>
            <form id="logoutForm" action="/providers/logout" method="post" class="logoutForm d-inline">
                <button type="submit" class="logoutButton btn btn-outline-danger">Logout</button>
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
            <li class="breadcrumb-item active" aria-current="page">Edit Profile</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!--Edit Provider Profile-->
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
        <div class="border p-4 mb-5" style="max-width: 500px; width: 100%;">
            <h2>Edit Profile</h2>
            <form action="/providers/profile/update" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${provider.id}">
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${provider.name}" required>
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" value="${provider.username}" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="${provider.email}" required>
                </div>
                <div class="mb-3">
                    <label for="phoneNumber" class="form-label">Phone Number</label>
                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${provider.phoneNumber}" required>
                </div>
                <input type="hidden" name="birthdate" value="${provider.birthdate}">
                <input type="hidden" name="password" value="${provider.password}">
                <input type="hidden" id="imagePath" name="imagePath" value="${(provider.profileImagePath)!'default-profile.png'}" readonly />
                <div class="mb-3">
                    <label for="imageFile" class="form-label">Profile Image</label>
                    <input type="file" class="form-control" id="imageFile" name="imageFile" accept="image/*">
                </div>
                <div class="mb-3">
                    <label for="bio" class="form-label">Bio</label>
                    <textarea class="form-control" id="bio" name="bio" rows="3">${provider.bio!''}</textarea>
                </div>
                <input type="hidden" name="listingsListed" value="${provider.listingsListed}">
                <input type="hidden" name="tradesCompleted" value="${provider.tradesCompleted}">
                <input type="hidden" name="collectionSize" value="${provider.collectionSize}">
                <div class="row align-items-center">
                    <div class="col mt-2">
                        <button type="submit" class="btn btn-primary flex-fill w-100">Save Changes</button>
                    </div>
                    <div class="col mt-2">
                        <a href="/providers/profile" class="btn btn-secondary flex-fill w-100">Cancel</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>