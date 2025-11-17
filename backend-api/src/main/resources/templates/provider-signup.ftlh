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
            <a class="navbar-brand" href="#">TradeTable</a>
        </div>
    </nav>
    <!--Signup Form-->
    <div class="container mt-2 d-flex justify-content-center align-items-center" style="min-height: 100vh;">
        <div class="w-75 border p-4 rounded">
          <h2 class="mb-4">Create Provider Account</h2>
          <!--need action to go to /providers/signup?error=password_mismatch if passwords do not match-->
          <form id="providerSignupForm" action="/providers/signup" method="post" enctype="multipart/form-data" class="row g-2" novalidate>
            <div class="col-md-6">
                <label for="name" class="form-label">Name</label>
                <input type="text" id="name" name="name" class="form-control" placeholder="John Doe" required>
                <#if signupError?? && signupError>
                  <div class="invalid-feedback d-block">Name required.</div>
                </#if>
            </div>

            <div class="col-md-6">
              <label for="username" class="form-label">Username</label>
              <input type="text" id="username" name="username" class="form-control" placeholder="johndoe" required>
              <#if signupError?? && signupError>
                <div class="invalid-feedback d-block">Username required.</div>
              </#if>
            </div>

            <div class="col-md-6">
              <label for="email" class="form-label">Email</label>
              <input type="email" id="email" name="email" class="form-control" placeholder="johndoe@example.com" required>
              <#if signupError?? && signupError>
                <div class="invalid-feedback d-block">Valid email required.</div>
              </#if>
            </div>

            <div class="col-md-6">
              <label for="phoneNumber" class="form-label">Phone Number</label>
              <input type="tel" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="(123) 456-7890" required>
              <#if signupError?? && signupError>
                <div class="invalid-feedback d-block">Phone number required.</div>
              </#if>
            </div>
            
            <div class="col-md-6">
              <label for="password" class="form-label">Password</label>
              <input type="password" id="password" name="password" class="form-control" required>
              <#if signupError?? && signupError>
                <div class="invalid-feedback d-block">Password required.</div>
              </#if>
            </div>

            <div class="col-md-6">
              <label for="confirm-password" class="form-label">Confirm Password</label>
              <input type="password" id="confirm-password" name="confirm-password" class="form-control" required>
              <#if signupError?? && signupError>
                <div class="invalid-feedback d-block">Please confirm your password.</div>
              </#if>
              <#if passwordMismatchError?? && passwordMismatchError>
                <div class="invalid-feedback d-block">Password does not match.</div>
              </#if>
            </div>

            <div class="col-md-6">
              <label for="birthdate" class="form-label">Birth Date</label>
              <input type="date" id="birthdate" name="birthdate" class="form-control" max="2007-11-13" required>
              <#if signupError?? && signupError>
                <div class="invalid-feedback d-block">Valid birth date required.</div>
              </#if>
            </div>

            <div class="col-md-6">
              <label for="imageFile" class="form-label">Profile Image</label>
              <input type="file" id="imageFile" name="imageFile" class="form-control" accept="image/*" required>
            </div>

            <div class="col-12">
              <label for="bio" class="form-label">Bio</label>
              <textarea id="bio" name="bio" class="form-control" rows="3"></textarea>
            </div>

            <div class="col-12 d-grid">
              <button type="submit" class="btn btn-primary">Sign Up</button>
            </div>

            <div class="col-12 text-center">
              <p class="small m-0">Already have an account? <a href="/providers/login">Log In</a></p>
            </div>
          </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <script src="/js-provider/provider.js"></script>
</body>