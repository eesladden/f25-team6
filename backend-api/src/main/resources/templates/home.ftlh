<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Home</title>
    <link rel="stylesheet" type="text/css" href="/css/provider-style.css">
</head>
<body style="background-image: url('/provider-images/tcg-bg.jpg'); background-size: cover; background-position: center; background-repeat: no-repeat; background-attachment: fixed; background-color: rgba(255, 255, 255, 0.8); background-blend-mode: lighten;">
    <!--Navbar with static title-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">TradeTable</a>
        </div>
    </nav>
    <!--Home Content-->
    <div class="container mt-4 d-flex justify-content-center align-items-center" style="min-height: 80vh;">
      <div class="card card-brown p-4" style="width: 470px;">
        <h2 class="mb-2 text-center">Welcome to TradeTable!</h2>
        <p class="text-center">Your one-stop platform for trading and collecting cards.</p>
        <div class="d-grid gap-2">
            <a href="/customers/login" class="btn btn-primary">Customer Login</a>
            <a href="/providers/login" class="btn btn-secondary">Provider Login</a>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>