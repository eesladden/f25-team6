<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Create New Card</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>
    <!---NAVBAR Start-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/providers/dashboard">TradeTable</a>
            <form id="logoutForm" action="/providers/logout" method="post" class="logoutForm d-inline">
                <button type="submit" class="logoutButton btn btn-outline-danger">Logout</button>
            </form>
            <a class="nav-link" href="/providers/profile">
                <!--should be the profile picture of the provider in session-->
                <img src="/profile-pictures/${(provider.profileImagePath)!'default-profile.png'}" alt="Profile" width="30" height="30" class="d-inline-block align-top">
            </a>
        </div>
    </nav>
    <!---NAVBAR End-->
    <!--Breadcrumbs Start-->
    <nav aria-label="breadcrumb" class="mt-4 ms-5">
        <ol class="breadcrumb ms-3">
            <li class="breadcrumb-item"><a href="/providers/dashboard">Dashboard</a></li>
            <li class="breadcrumb-item"><a href="/cards/collection">My Collection</a></li>
            <li class="breadcrumb-item active" aria-current="page">Create New Card</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!---Create New Card Form-->
    <!--Needs to be in a rectangular bordered container in the horizontal and vertical center of the page-->
    <div class="container mt-4 d-flex justify-content-center align-items-center" style="min-height: 80vh;">
        <div class="w-50 border p-4 rounded">
            <h2>Create New Card</h2>
            <form action="/cards" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="imageFile" class="form-label">Upload Image</label>
                <input type="file" class="form-control" id="imageFile" name="imageFile" accept="image/*" required>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Charizard" required>
                </div>
                <div class="mb-3">
                    <label for="game" class="form-label">Game</label>
                    <input type="text" class="form-control" id="game" name="game" placeholder="Pokémon" required>
                </div>
                <div class="mb-3">
                    <label for="set" class="form-label">Set</label>
                    <input type="text" class="form-control" id="set" name="set" placeholder="Scarlet & Violet" required>
                </div>
                <div class="mb-3">
                    <label for="rarity" class="form-label">Rarity</label>
                    <input type="text" class="form-control" id="rarity" name="rarity" placeholder="Ultra Rare" required>
                </div>
                <!--Create card will be added to your collection message-->
                <div class="mb-3">
                    <div class="form-text">The new card will be added to your collection upon creation.</div>
                </div>
                <div class="mb-3 text-center">
                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary flex-fill" style="width: 90px;">Create Card</button>
                    <a href="/cards/collection" class="btn btn-secondary flex-fill">Cancel</a>
                </div>
            </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>