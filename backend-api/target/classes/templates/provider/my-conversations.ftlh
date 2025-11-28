<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Messages</title>
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
            <li class="breadcrumb-item"><a href="/providers/dashboard">Dashboard</a></li>
            <li class="breadcrumb-item active" aria-current="page">Messages</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!--Messages-->
    <div class="container mt-4">
        <h1 class="mb-4">Messages</h1>
        <div class="list-group">
            <#if provider.conversations?size == 0>
                <p class="text-muted">You have no conversations yet.</p>
            <#else>
                <#list provider.conversations as conversation>
                    <a href="/providers/messages/conversations/my-conversations/${conversation.id}" class="list-group-item list-group-item-action mb-1 rounded">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">Conversation with ${conversation.customer.username}</h5>
                            <small class="text-muted">${conversation.messages?size} messages</small>
                        </div>
                        <div class="d-flex w-100 justify-content-between mt-1">
                            <p class="mb-1 text-truncate"><strong class="me-1">For Listing:</strong> ${conversation.listing.card.name} ・ ${conversation.listing.card.game} ・ ${conversation.listing.card.set} (${conversation.listing.card.rarity})</p>
                            <small class="text-muted">Last updated: ${conversation.lastUpdatedString}</small>
                        </div>
                    </a>
                </#list>
            </#if>
        </div>
    </div>
    <!--Messages End-->
    <!-- Bootstrap JS --> 
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/provider-logout.js"></script>
</body>
</html>