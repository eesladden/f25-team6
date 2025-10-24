# LocalHarvest Hub API Documentation

## Provider API Endpoints

### Create Provider
```http
POST /api/providers
Content-Type: application/json

{
    "name": "John Doe",
    "username": "johndoe",
    "email": "johndoe@example.com",
    "phoneNumber": "123-456-7890",
    "password": "securepassword",
    "birthdate": "1990-01-01"
}
```

### Update Provider
```http
PUT /api/providers/{id}
Content-Type: application/json

{
    "name": "John Doe",
    "username": "UPDATEDjohndoe",
    "email": "johndoe@update.com",
    "phoneNumber": "987-654-3210",
    "password": "securepassword",
    "birthdate": "1990-01-01"
}
```

### Update Password
```http
PUT /api/providers/{id}/password?oldPassword={oldPassword}&newPassword={newPassword}
```

### Get Provider
```http
GET /api/provider/{id}
```

## Card API Endpoints

### Create Card
```http
POST /api/cards
Content-Type: application/json

{
  "providers": [],
  "name": "Blue-Eyes White Dragon",
  "deck": "Legend of Blue Eyes White Dragon",
  "game": "Yu-Gi-Oh!",
  "rarity": "Ultra Rare"
}
```

### Update Card
```http
PUT /api/cards/{id}
Content-Type: application/json

{
    "providers": [],
    "name": "UPDATED Blue-Eyes White Dragon",
    "deck": "Legend of Blue Eyes White Dragon",
    "game": "Yu-Gi-Oh!",
    "rarity": "Ultra Rare"
}
```

### Get All Cards
```http
GET /api/card
```

### Get Card
```http
GET /api/farms/{id}
```

### Delete Card
```http
DELETE /api/cards/{id}
```

### Search For Card
```http
GET /api/cards/search?name={name}&deck={deck}&game={game}&rarity={rarity}
```

### Add Card To Provider's Collection
```http
POST /api/cards/{cardId}/providers/{providerId}
```

### Remove Card From Provider's Collection
```
DELETE /api/cards/{cardId}/providers/{providerId}
```

## Listing Endpoints

### Create Listing
```http
POST /api/listings
Content-Type: application/json

{
    "card": { 
        "id": 1 
    },
    "provider": { 
        "id": 1 
    },
    "condition": "Near Mint",
    "grade": "Gem Mint 10",
    "marketPrice": 15.0,
    "highPrice": 20.0,
    "lowPrice": 10.0,
    "isAvailable": true,
    "isForSale": true,
    "tradingFor": "N/A",
    "cityName": "New York",
    "stateName": "NY",
}

OR

{
    "card": { 
        "id": 1 
    },
    "provider": { 
        "id": 1 
    },
    "condition": "Lightly Played",
    "grade": "Near Mint 8",
    "marketPrice": 10.0,
    "highPrice": 15.0,
    "lowPrice": 5.0,
    "isAvailable": true,
    "isForSale": false,
    "tradingFor": "Looking for Rare Cards",
    "cityName": "Los Angeles",
    "stateName": "CA",
}
```

### Update Listing
```http
PUT /api/listings/{id}
Content-Type: application/json

{
    "card": { 
        "id": 1 
    },
    "provider": { 
        "id": 1 
    },
    "condition": "Mint",
    "grade": "UPDATED Mint 10",
    "marketPrice": 15.0,
    "highPrice": 20.0,
    "lowPrice": 10.0,
    "isAvailable": true,
    "isForSale": true,
    "tradingFor": "N/A",
    "cityName": "New York",
    "stateName": "NY",
}
```

### Get Listing
```http
GET /api/listings/{id}
```

## Search For Listing
```http
GET /api/listings/search?cityName={cityName}&condition={condition}&grade={grade}
```

## Get All For-Sale Listings
```http
GET /api/listings/for-sale
```

## Get All For-Trade Listings
```http
GET /api/listings/for-trade
```

## Get All Most Recently Updated Or Created Listings
```http
GET /api/listings/most-recent
```

## Get All Avaliable Listings
```http
GET /api/listings/available
```

## Get All Listings In Market Price Ascending Order
```http
GET /api/listings/market-price/asc
```

## Get All Listings In Market Price Descending Order
```http
GET /api/listings/market-price/desc
```

## Get All Listings In High Price Ascending Order
```http
GET /api/listings/high-price/asc
```

## Get All Listings In High Price Descending Order
```http
GET /api/listings/high-price/desc
```

## Get All Listings In Low Price Ascending Order
```http
GET /api/listings/low-price/asc
```

## Get All Listings In Low Price Descending Order
```http
GET /api/listings/low-price/desc
```

## Get All Listings From A Specific Provider
```http
GET /api/listings/provider/1
```