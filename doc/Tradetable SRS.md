# Software Requirements Specification
## For Tradetable

Version 0.1  
Prepared by Emma Sladden and Taylor Edwards
CSC 340 
September 14, 2025  

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Introduction](#1-introduction)
  * 1.1 [Document Purpose](#11-document-purpose)
  * 1.2 [Product Scope](#12-product-scope)
  * 1.3 [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
  * 1.4 [References](#14-references)
  * 1.5 [Document Overview](#15-document-overview)
* 2 [Product Overview](#2-product-overview)
  * 2.1 [Product Functions](#21-product-functions)
  * 2.2 [Product Constraints](#22-product-constraints)
  * 2.3 [User Characteristics](#23-user-characteristics)
  * 2.4 [Assumptions and Dependencies](#24-assumptions-and-dependencies)
* 3 [Requirements](#3-requirements)
  * 3.1 [Functional Requirements](#31-functional-requirements)
    * 3.1.1 [User Interfaces](#311-user-interfaces)
    * 3.1.2 [Hardware Interfaces](#312-hardware-interfaces)
    * 3.1.3 [Software Interfaces](#313-software-interfaces)
  * 3.2 [Non-Functional Requirements](#32-non-functional-requirements)
    * 3.2.1 [Performance](#321-performance)
    * 3.2.2 [Security](#322-security)
    * 3.2.3 [Reliability](#323-reliability)
    * 3.2.4 [Availability](#324-availability)
    * 3.2.5 [Compliance](#325-compliance)
    * 3.2.6 [Cost](#326-cost)
    * 3.2.7 [Deadline](#327-deadline)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
| Emma |  9/15   |     Initial SRS     |    1.0    |
|      |         |                     |           |
|      |         |                     |           |

## 1. Introduction

### 1.1 Document Purpose
The purpose of this Software Requirements Document is to descirbe to customer-view and provider-view for the Tradetable application. The customer requirements describe what is needed on the customer's side. The provider requirements describe what is needed on the provider side.

### 1.2 Product Scope
The purpose of Tradetable is to provide a tradeable marketplace for popular card-based games and entertainment to its customers and providers.
The system used is a web-based application which allows widespread access and a simplifies functionality. The main objective is to create a space for people with common interests to gather and easily communicate trades.

### 1.3 Definitions, Acronyms and Abbreviations                               
| Reference            | Definition                                                                                                                |
|----------------------|---------------------------------------------------------------------------------------------------------------------------|
| Java                 | An object-oriented programming language, which will be used for the backend of the application.                           |
| HTML                 | Stands for Hypertext Markup Language. This is the code used to give the web application structure.                        |
| CSS                  | Stands for Cascading Style Sheets. This will be used to stylize and implement design into the application                 |
| SpringBoot           | An open-source, Java-based framework. This will be used to create and run the application.                                |
| Spring MVC           | MVC stands for Model-View-Controller. This will be used for the architectual design pattern of the application.           |
| Spring Boot DevTools | Provide an enhanced development experience. This will be used to develop to system.                                       |
| Spring Web           | Used to build web applications using Spring MVC. This will be used as a dependency.                                       |
| API                  | Stands for Application Programming Interface. This will be used to interface the frontend and backend of the application. |
| Javascript           | An object-oriented programming language used to create interactivity in web browsers. Will be used with HTML and CSS.     |
| VS Code              | An integrated development environment (IDE) for Java. This is where the system will be created.                           |
|                      |                                                                                                                           |

### 1.4 References
https://spring.io/guides

### 1.5 Document Overview
Section 1 is just a general overview of the system, its purpose, and what it is. Section 2 includes more about the specific application being created, including functions and constraints. Section 3 goes into more detail about the specific funtions of the system, broken down into customer fuctions and provider functions.

## 2. Product Overview
The Tradetable application is a web-based platform designed to allow the user to communicate and trade cards. Users can create customizable profiles and listings for cards for trade, then negotiate price and meeting place. They will also be able to view traders in there local area.

### 2.1 Product Functions
* Profile Management (customer/provider)
* Listing & Search (browse, filter, sort)
* Offer/Counter-Offer System (barter only)
* Estimated Value Guidance (mock index or TCGplayer's offical API)
* Watchlists & Notifications
* Review & Reputation System
* In-App Messaging

### 2.2 Product Constraints
* Implemented as a web application (browser-based).
* Backend hosted on a cloud service (e.g., Firebase, AWS, or UNCG server).
* No real money transactions; all trades are barter-only.
* Dependence on TCGplayer API for price guidance (subject to rate limits & ToS).
  
### 2.3 User Characteristics
  * Customer users
* Role & purpose: Customers are collectors who primarily use the platform to browse, discover, and trade cards.
* Functions used: Search listings, view card details, make and respond to trade offers, maintain watchlists, message providers, and leave reviews.
* Frequency of use: May log in a few times per week depending on trade activity or new listings.
* Technical expertise: Moderate; most will have general web browsing and account management skills.
* Privileges: Limited to their own profiles, offers, and reviews. Cannot edit other users’ listings or reviews.

  * Provider users
* Role & purpose: Providers are users who actively list and manage cards for trade and interact with multiple customers.
* Functions used: Create, update, and remove listings. Respond to trade offers and view customer statistics. Reply to reviews and manage their profile's reputation.
* Frequency of use: Typically more frequent than customers. Daily or multiple times per week to manage inventory and respond to offers.
* Technical expertise: Slightly higher than customers. May be familiar with inventory systems or online marketplace platforms.
* Privileges: Can create and manage multiple listings.


### 2.4 Assumptions and Dependencies
* Assume continuous internet access.
* Dependence on TCGplayer API for optional live pricing.
* Mock index ensures functionality even if live API is unavailable.
* Users are responsible for physical shipping; Trade Table does not provide a check out or payment method. Users must decide via website DM how they want to exhange their products.

## 3. Requirements

### 3.1 Functional Requirements 
* Customer Role
- FR1: The system shall allow customers to create and modify profiles (avatar, preferred sets, region).
- FR2: The system shall allow customers to browse/search listings by game, set, rarity, grade, condition, and estimated value.
- FR3: The system shall allow customers to create watchlists and saved searches with notifications.
- FR4: The system shall allow customers to write reviews of providers after trades.

* Provider Role
- FR5: The system shall allow providers to create, modify, and remove profiles.
- FR6: The system shall allow providers to create listings with details (set, rarity, grade, photos, estimated value).
- FR7: The system shall allow providers to view customer statistics (trades, ratings, reviews).
- FR8: The system shall allow providers to reply to reviews.

* Shared Functions
- FR9: The system shall support barter trades (cards for cards, bundles allowed).
- FR10: The system shall display estimated values using TCGplayer data (Market, Low, Mid, High) when available; otherwise falls back to mock values.
- FR11: The system shall allow users to create, send, and counter offers.
- FR12: The system shall allow both parties to accept an offer, creating a Trade record.
- FR13: The system shall provide a shipping panel (mark Shipped/Received, attach tracking numbers).
- FR14: The system shall allow users to complete trades and leave reviews.
- FR15: The system shall include in-app messaging.
- FR16: The system shall maintain a reputation system based on reviews.
- FR17: The system shall not include checkout, payment, or invoices (barter only).
- FR18: The system shall show the source (TCGplayer) and timestamp for live estimates.
- FR19: The system shall fall back to mock values if the TCGplayer API fails or rate limits.
- FR20: The system shall provide a toggle for pricing mode (live or mock).
- FR21: The system shall allow users to report a trade if the other party does not fulfill within a reasonable timeframe.

#### 3.1.1 User interfaces
* Homepage: Browse listings.
* Profile Page: Edit details.
* Listing Page: View/add/filter cards.
* Offer Panel: Build/counter offers with value meter.
* Trade Dashboard: Track status (Pending to Countered to Agreed to Completed).
* Messaging: Inbox.
* Review Section: Leave/view reviews.

#### 3.1.2 Hardware interfaces
* Runs on PCs, laptops, smartphones with internet.

#### 3.1.3 Software interfaces
* TCGplayer API: Provides pricing (product and condition). Authenticate via keys for a temporary password (bearer token).
* Backend Proxy: Secures API keys, caches results, and enforces rate limits.
* Mock Index: Fallback pricing dataset (JSON/DB).

### 3.2 Non Functional Requirements 

#### 3.2.1 Performance
- NFR0: This system shall should use a maxiumum of 300 MB of memory 
- NFR1: A novice user of this system should take a maximum of 5 minutes to add and manage trading card listings 
- NFR1: An experienced user of this system should take a maximum of 2 minute to add and manage trading card listings 

#### 3.2.2 Security
- NFR2: This system shall only allow authorized user with a profile to access it

#### 3.2.3 Reliability
- NFR3: This system shall be tested throughly to ensure functionality before release
- NFR4: If this system has an issue, it shall be handled in a timely manner

#### 3.2.4 Availability
- NFR5: This system shall be avaliabe to users at all hours of the day, whenever they would like to access it

#### 3.2.5 Compliance

#### 3.2.6 Cost
- NFR6: This sytem shall not require any money to be made

#### 3.2.7 Deadline
- NFR7: This system shall be completed by the due date in December 2025
