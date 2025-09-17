# Software Requirements Specification
## For <project name>

Version 0.1  
Prepared by <author>  
<organization>  
<date created> 

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
|      |         |                     |           |
|      |         |                     |           |
|      |         |                     |           |

## 1. Introduction

### 1.1 Document Purpose
Describe the purpose of the SRS and its intended audience.

### 1.2 Product Scope
Identify the product whose software requirements are specified in this document, including the revision or release number. Explain what the product that is covered by this SRS will do, particularly if this SRS describes only part of the system or a single subsystem. 
Provide a short description of the software being specified and its purpose, including relevant benefits, objectives, and goals. Relate the software to corporate goals or business strategies. If a separate vision and scope document is available, refer to it rather than duplicating its contents here.

### 1.3 Definitions, Acronyms and Abbreviations                                                                                                                                                                          |

### 1.4 References
List any other documents or Web addresses to which this SRS refers. These may include user interface style guides, contracts, standards, system requirements specifications, use case documents, or a vision and scope document. Provide enough information so that the reader could access a copy of each reference, including title, author, version number, date, and source or location.

### 1.5 Document Overview
Describe what the rest of the document contains and how it is organized.

## 2. Product Overview
This section should describe the general factors that affect the product and its requirements. This section does not state specific requirements. Instead, it provides a background for those requirements, which are defined in detail in Section 3, and makes them easier to understand.

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
If there are performance requirements for the product under various circumstances, state them here and explain their rationale, to help the developers understand the intent and make suitable design choices. Specify the timing relationships for real time systems. Make such requirements as specific as possible. You may need to state performance requirements for individual functional requirements or features.

#### 3.2.2 Security
Specify any requirements regarding security or privacy issues surrounding use of the product or protection of the data used or created by the product. Define any user identity authentication requirements. Refer to any external policies or regulations containing security issues that affect the product. Define any security or privacy certifications that must be satisfied.

#### 3.2.3 Reliability
Specify the factors required to establish the required reliability of the software system at time of delivery.

#### 3.2.4 Availability
Specify the factors required to guarantee a defined availability level for the entire system such as checkpoint, recovery, and restart.

#### 3.2.5 Compliance
Specify the requirements derived from existing standards or regulations

#### 3.2.6 Cost
Specify monetary cost of the software product.

#### 3.2.7 Deadline
Specify schedule for delivery of the software product.
