<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>TradeTable - Customer</title>

  <!-- Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
        crossorigin="anonymous">

  <link rel="stylesheet" href="/css/provider-style.css">
</head>
<body style="background-image: url('/provider-images/tcg-bg.jpg');
             background-size: cover;
             background-position: center;
             background-repeat: no-repeat;
             background-attachment: fixed;
             background-color: rgba(255, 255, 255, 0.8);
             background-blend-mode: lighten;">

  <!-- Navbar -->
  <nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">TradeTable</a>
      <div class="d-flex">
        <a class="nav-link" href="/customer/dashboard">Dashboard</a>
        <a class="nav-link" href="/customer/wishlist">Wishlist</a>
        <a class="nav-link" href="/customer/offers">My Offers</a>
        <a class="nav-link" href="/customer/messages/conversations/my-conversations">Messages</a>
        <a class="nav-link" href="/customer/reviews/my-reviews">My Reviews</a>
        <!-- Optional: mock login link for testing -->
        <a class="btn btn-outline-primary ms-2" href="/customer/mock-login">Mock Login</a>
      </div>
    </div>
  </nav>

  <!-- Flash messages -->
  <div class="container mt-3">
    <#if msg??>
      <div class="alert alert-success" role="alert">
        ${msg}
      </div>
    </#if>
    <#if err??>
      <div class="alert alert-danger" role="alert">
        ${err}
      </div>
    </#if>
  </div>

  <!-- Main content -->
  <div class="container mt-4 mb-5">
    <div class="card shadow-sm">
      <div class="card-body">
        <@renderContent/>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
          crossorigin="anonymous"></script>
  <script src="/js/app.js"></script>
</body>
</html>
