<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - My Reviews</title>
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
            <li class="breadcrumb-item"><a href="/providers/dashboard">Provider Dashboard</a></li>
            <li class="breadcrumb-item active" aria-current="page">My Reviews</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!--My Reviews-->
    <div class="container mt-4">
        <h1 class="mb-4">My Reviews</h1>
        <!--row-->
        <div class="row mb-3">
            <div class="col-md-3">
                <div class="input-group">
                    <form id="filterForm" action="/reviews/my-reviews/sort" method="get" class="d-flex w-100 gap-2">
                        <select class="form-select rounded" id="sortSelect" name="order">
                            <option value="" selected>Sort by Date</option>
                            <option value="desc">Newest First</option>
                            <option value="asc">Oldest First</option>
                        </select>
                        <button class="btn btn-success rounded" type="submit" id="sortButton">Apply</button>
                    </form>
                </div>
            </div>
            <div class="col">
                <div class="input-group">
                    <form id="searchForm" action="/reviews/my-reviews/search" method="get" class="d-flex w-100 gap-2">
                        <input type="text" class="form-control rounded" placeholder="Search reviews..." aria-label="Search reviews" id="searchInput" name="content">
                        <button class="btn btn-primary rounded" type="submit" id="searchButton">Search</button>
                        <a href="/reviews/my-reviews" class="btn btn-dark rounded">Clear</a>
                    </form>
                </div>
            </div>
        </div>
        <div class="list-group">
            <#if provider.receivedReviews?size == 0>
                <p class="text-muted">You have not received any reviews yet.</p>
            <#else>
                <#list reviews as review>
                    <div class="list-group-item mb-3 rounded">
                        <a href="/reviews/my-reviews/rating?rating=${review.rating}" style="text-decoration: none; color: inherit;">
                            <div>
                                <strong>Rating:</strong>
                                <#list 1..5 as star>
                                    <#if star <= review.rating>
                                        <span class="text-warning">&#9733;</span>
                                    <#else>
                                        <span class="text-secondary">&#9734;</span>
                                    </#if>
                                </#list>
                                <span class="ms-1 align-middle fw-bold" style="color: black; font-family: Arial, sans-serif;">${review.rating} / 5</span>
                            </div>
                        </a>
                        <p class="mb-1 mt-2">${review.comment}</p>
                        <small class="text-muted">From: ${review.customer.username} | Date: ${review.createdAtString}</small>
                        <#if review.tags?has_content>
                            <div class="mt-2">
                                <strong>Tags:</strong>
                                <#list review.tags as tag>
                                <a href="/reviews/my-reviews/filter?tag=${tag}" style="text-decoration: none;">
                                    <#if tag == "HIGH_QUALITY">
                                        <span class="badge bg-info text-dark">HIGH QUALITY</span>
                                    <#else>
                                        <span class="badge bg-info text-dark">${tag}</span>
                                    </#if>
                                </a>
                                </#list>
                            </div>
                        </#if>
                        <#if review.providerResponse??>
                            <div class="mt-3 mb-2 p-3 bg-light border rounded">
                                <h6>My Response:</h6>
                                <p>${review.providerResponse}</p>
                                <small class="text-muted">Date: ${review.responseAtString}</small>
                            </div>
                        <#else>
                            <form action="/reviews/my-reviews/${review.id}/reply" method="post" class="mt-3 pb-2">
                                <div class="mb-3">
                                    <label for="response-${review.id}" class="form-label">Respond to this review:</label>
                                    <textarea class="form-control" id="response-${review.id}" name="response" rows="3" required></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary">Submit Response</button>
                            </form>
                        </#if>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
    <script src="/js-provider/provider-logout.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>