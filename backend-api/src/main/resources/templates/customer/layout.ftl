<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>TradeTable</title>
  <link rel="stylesheet" href="/css/app.css">
</head>
<body>
  <nav>
    <a href="/customer/dashboard">Dashboard</a>
    <a href="/customer/wishlist">Wishlist</a>
    <a href="/customer/offers">My Offers</a>
  </nav>
  <main>
    <#-- content slot -->
    <@renderContent/>
  </main>
  <script src="/js/app.js"></script>
</body>
</html>
