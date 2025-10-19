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
|  Emma  |10/19    | Initial Design      |    1      |
|        |         |                     |           |
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

## 3. UML Class Diagram
![UML Class Diagram](https://github.com/eesladden/f25-team6/blob/main/doc/Object-Oriented-Design/class-diagram.png)
## 4. Database Schema
![UML Class Diagram](https://github.com/eesladden/f25-team6/blob/main/doc/Object-Oriented-Design/schema.png)