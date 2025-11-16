<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Log In</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <!--Navbar with static title-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">TradeTable</a>
        </div>
    </nav>
    <!--Login Form-->
    <div class="container mt-4 d-flex justify-content-center align-items-center" style="min-height: 80vh;">
        <div class="card p-4" style="width: 400px;">
            <h2 class="mb-4 text-center">Provider Log In</h2>
            <form action="/providers/login" method="post" class="needs-validation" novalidate>
            <!--Needs to have a message appear beneath when field is empty, when username doesn't exist, or when password is incorrect-->
            <div class="mb-3">
              <label for="username" class="form-label">Username</label>
              <input type="text" id="username" name="username" class="form-control" required>
              <#if loginError?? && loginError>
                <div class="invalid-feedback d-block">Invalid username or password.</div>
              </#if>
            </div>

            <div class="mb-3">
              <label for="password" class="form-label">Password</label>
              <input type="password" id="password" name="password" class="form-control" required>
              <#if loginError?? && loginError>
                <div class="invalid-feedback d-block">Invalid username or password.</div>
              </#if>
            </div>
            <div class="d-grid">
              <button type="submit" class="btn btn-primary">Log In</button>
            </div>

            <div class="text-center mt-3">
              <p class="small m-0">Don't have an account? <a href="/providers/signup">Sign Up</a></p>
            </div>
          </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>