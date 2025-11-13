<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>TradeTable - Sign Up</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <!--Navbar with static title-->
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/home">TradeTable</a>
        </div>
    </nav>
    <!--Signup Form-->
    <div class="container mt-4">
        <h1 class="mb-4">Create Provider Account</h1>
        <form action="/providers/signup" method="post" enctype="multipart/form-data" class="row g-3 needs-validation" novalidate>
        <div class="col-md-6">
            <label for="name" class="form-label">Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
            <div class="invalid-feedback">Name required.</div>
        </div>

        <div class="col-md-6">
          <label for="username" class="form-label">Username</label>
          <input type="text" id="username" name="username" class="form-control" required>
          <div class="invalid-feedback">Username required.</div>
        </div>

        <div class="col-md-6">
          <label for="email" class="form-label">Email</label>
          <input type="email" id="email" name="email" class="form-control" required>
          <div class="invalid-feedback">Valid email required.</div>
        </div>

        <div class="col-md-6">
          <label for="phoneNumber" class="form-label">Phone Number</label>
          <input type="tel" id="phoneNumber" name="phoneNumber" class="form-control" required>
          <div class="invalid-feedback">Phone number required.</div>
        </div>

        <div class="col-md-6">
          <label for="password" class="form-label">Password</label>
          <input type="password" id="password" name="password" class="form-control" required>
          <div class="invalid-feedback">Password required.</div>
        </div>

        <div class="col-md-6">
          <label for="confirm-password" class="form-label">Confirm Password</label>
          <input type="password" id="confirm-password" name="confirm-password" class="form-control" required>
          <div class="invalid-feedback">Please confirm your password.</div>
        </div>

        <div class="col-md-6">
          <label for="birthdate" class="form-label">Birth Date</label>
          <input type="date" id="birthdate" name="birthdate" class="form-control" max="2007-11-13" required>
          <div class="invalid-feedback">Please provide a valid birth date.</div>
        </div>

        <div class="col-md-6">
          <label for="imageFile" class="form-label">Profile Image</label>
          <input type="file" id="imageFile" name="imageFile" class="form-control" accept="image/*" required>
          <div class="invalid-feedback">Profile image required.</div>
        </div>

        <div class="col-12">
          <label for="bio" class="form-label">Bio</label>
          <textarea id="bio" name="bio" class="form-control" rows="3" required></textarea>
        </div>

        <div class="col-12 d-grid">
          <button type="submit" class="btn btn-primary">Sign Up</button>
        </div>

        <div class="col-12 text-center">
          <p class="small m-0">Already have an account? <a href="/providers/login">Log In</a></p>
        </div>
      </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/provider.js"></script>
</body>