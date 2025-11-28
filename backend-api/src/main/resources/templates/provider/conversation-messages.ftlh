<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Conversation with ${conversation.customer.username}</title>
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
            <li class="breadcrumb-item"><a href="/providers/messages/conversations/my-conversations">Messages</a></li>
            <li class="breadcrumb-item active" aria-current="page">Conversation with ${conversation.customer.username}</li>
        </ol>
    </nav>
    <!--Breadcrumbs End-->
    <!--Conversation Messages-->
    <div class="container mt-4">
        <div class="card mb-4">
            <div class="card-header d-flex justify-content-between align-items-center" style="background-color: #f8f9fa;">
                <h5 class="mb-0">Conversation with ${conversation.customer.username}</h5>
                <form action="/providers/messages/conversations/my-conversations/${conversation.id}/delete" method="post" onsubmit="return confirm('Are you sure you want to delete this conversation? This action cannot be undone.');">
                    <button type="submit" class="btn btn-danger btn-sm">Delete Conversation</button>
                </form>
            </div>
            <div class="card-body" style="height: 500px; overflow-y: auto; background-color: #ffffff;">
                <#if messages?size == 0>
                    <p class="text-muted">No messages in this conversation yet.</p>
                </#if>
                <#list messages as message>
                    <#if message.sender == "PROVIDER">
                        <div class="d-flex mb-3 justify-content-end">
                            <div class="p-3 rounded bg-primary text-white border" style="max-width: 70%;">
                                <p class="mb-1">${message.content}</p>
                                <small class="text-muted">${message.createdAtString}</small>
                            </div>
                        </div>
                    <#else>
                        <div class="d-flex mb-3 justify-content-start">
                            <div class="p-3 rounded bg-light text-dark border" style="max-width: 70%;">
                                <p class="mb-1">${message.content}</p>
                                <small class="text-muted">${message.createdAtString}</small>
                            </div>
                        </div>
                    </#if>
                </#list>
            </div>
            <div class="card-footer align-middle">
                <form action="/providers/messages/conversations/${conversation.id}/send" method="post" class="d-flex mb-2 mt-1" style="height: 34px;">
                    <input type="hidden" name="customer.id" value="${conversation.customer.id}">
                    <textarea class="form-control me-2" name="content" rows="1" placeholder="Type your message here..." required></textarea>
                    <button type="submit" class="btn btn-success">Send</button>
                </form>
            </div>
        </div>
    </div>
    <!--Conversation Messages End-->
    <!-- Bootstrap JS --> 
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/provider-logout.js"></script>
</body>
</html>