<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Edit Card</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
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
    <!---Edit Card Form-->
    <div class="container mt-4 d-flex justify-content-center align-items-center" style="min-height: 80vh;">
        <div class="w-50 border p-4 rounded">
            <h2>Edit Card</h2>
            <form action="/cards/${card.id?c}/update" method="post" enctype="multipart/form-data" novalidate>
                <input type="hidden" id="id" name="id" value="${card.id?c}">
                <input type="hidden" id="imagePath" name="imagePath" value="${(card.imagePath)}" readonly />
                <div class="mb-3">
                    <label for="imageFile" class="form-label">Card Image</label>
                    <input type="file" class="form-control" id="imageFile" name="imageFile" accept="image/*">
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${card.name}" placeholder="${card.name!'Charizard'}" required>
                    <#if editError??>
                        <div class="text-danger mt-2">Card name cannot be empty.</div>
                    </#if>
                </div>
                <div class="mb-3">
                    <label for="game" class="form-label">Game</label>
                    <input type="text" class="form-control" id="game" name="game" value="${card.game}" placeholder="${card.game!'Pokémon'}" required>
                    <#if editError??>
                        <div class="text-danger mt-2">Card game cannot be empty.</div>
                    </#if>
                </div>
                <div class="mb-3">
                    <label for="set" class="form-label">Set</label>
                    <input type="text" class="form-control" id="set" name="set" value="${card.set}" placeholder="${card.set!'Scarlett & Violet'}" required>
                    <#if editError??>
                        <div class="text-danger mt-2">Card set cannot be empty.</div>
                    </#if>
                </div>
                <div class="mb-3">
                    <label for="rarity" class="form-label">Rarity</label>
                    <input type="text" class="form-control" id="rarity" name="rarity" value="${card.rarity}" placeholder="${card.rarity!'Ultra Rare'}" required>
                    <#if editError??>
                        <div class="text-danger mt-2">Card rarity cannot be empty.</div>
                    </#if>
                </div>
                <button type="submit" class="btn btn-primary">Update Card</button>
            </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/logout.js"></script>
</body>
</html>