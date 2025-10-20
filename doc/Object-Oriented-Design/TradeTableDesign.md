# TradeTable - Software Design 

Version 1  
Prepared by Emma Sladden & Taylor Edwards\
TradeTable\
Oct 19, 2025

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Product Overview](#1-product-overview)
* 2 [Use Cases](#2-use-cases)
  * 2.1 [Use Case Model](#21-use-case-model)
  * 2.2 [Use Case Descriptions](#22-use-case-descriptions)
    * 2.2.1 [Actor: Farmer](#221-actor-farmer)
    * 2.2.2 [Actor: Customer](#222-actor-customer) 
* 3 [UML Class Diagram](#3-uml-class-diagram)
* 4 [Database Schema](#4-database-schema)

## Revision History
| Name   | Date    | Reason For Changes  | Version   |
| ------ | ------- | ------------------- | --------- |
|  Emma  |10/19    | Initial Design      |     1     |
| Taylor |10/20    | Customer Use cases  |     1     |
|        |         |                     |           |

## 1. Product Overview

## 2. Use Cases
### 2.1 Use Case Model
![Use Case Model](https://github.com/eesladden/f25-team6/blob/main/doc/Object-Oriented-Design/use-cases.png)

### 2.2 Use Case Descriptions

#### 2.2.1 Actor: Provider

##### 2.2.1.1 Sign Up
A provider shall be able to sign up to create their profile with their name, username, email, password, phone number, and birthdate. Emails, usernames, and phone numbers must be unique.

##### 2.2.1.2 Log In
A provider shall be able to sign in using their registered username and password. After logging in, the provider shall be directed to the landing page, have access to their dashboard, and be able to view their profile.

##### 2.2.1.3 Update Profile
A provider shall be able to modify most of the information they used to create their profile, including their name, username, email, password, and phone number. They will able be able to add a bio and profile picture. However, they will not be able to change their birthdate.

##### 2.2.1.4 Messaging
The provider shall be able to be notified when they have received a new message about a listing, view all of their messages, and respond to any messages.

##### 2.2.1.5 Manage Collection
The provider shall be able to add cards to their own personal collection list.

##### 2.2.1.7 Create Listing
The provider shall be able to create listings from existing cards in their collection.

##### 2.2.1.6 Manage Reviews
The provider shall be able to view their rating, see all reviews, and reply to any reviews.

##### 2.2.1.8 Manage Listings
The provider shall be able to see all their listings, edit any listing, or remove any listing when desired.


#### 2.2.2 Actor: Customer

##### 2.2.2.1 Sign Up and Log In
A customer shall be able to create an account by entering their name, username, email, password, and optional profile picture. After registration, the customer shall be able to log in using their username and password to access their dashboard and marketplace.

##### 2.2.2.2 View and Edit Profile
A customer shall be able to view their profile information, update details such as display name, email, password, and upload or change their profile picture.

##### 2.2.2.3 Browse and Search Listings
A customer shall be able to browse all active listings and search by filters including game, card name, rarity, set, condition, or price range. Customers can also sort results by newest, lowest price, or closest match.

##### 2.2.2.4 View Listing Details
A customer shall be able to click on a listing to view full details including images, card condition, price or trade value, seller rating, and item description. From this page customers can contact the provider, add the item to their watchlist, or initiate a trade offer.

##### 2.2.2.5 Manage Watchlist
A customer shall be able to add or remove listings from their watchlist. They will be able to see their saved items in one place and receive updates if an item’s availability or price changes.

##### 2.2.2.6 Create, Counter, or Accept Trade Offer
A customer shall be able to make an offer on a listing by selecting cards or items from their own collection to propose a trade. The customer shall also be able to counter a received offer or accept one to finalize a trade.

##### 2.2.2.7 Messaging
A customer shall be able to message the provider directly within the platform to negotiate trades, clarify card conditions, or arrange shipping details. Messages will be tied to active listings or trade offers.

##### 2.2.2.8 Leave Review
A customer shall be able to leave a rating and review for a provider after a completed trade, commenting on the accuracy of the listing, communication, and overall experience.


## 3. UML Class Diagram
![UML Class Diagram](https://github.com/eesladden/f25-team6/blob/main/doc/Object-Oriented-Design/class-diagram.png)
## 4. Database Schema
![UML Class Diagram](https://github.com/eesladden/f25-team6/blob/main/doc/Object-Oriented-Design/schema.png)