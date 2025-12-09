# TradeTable Requirements Testing
## Actors
- Provider
- Customer

### Use Cases

#### 1. Provider: Create provider profile & create first listings
1. Provider P1 signs up for the first time and creates a provider profile.
2. P1 logs in to their new account and navigates to their dashboard.
3. P1 adds two cards, C1 and C2, from their collection.
4. P1 then removes C2 from their collection.
5. P1 creates a card C3, and then uses C1 and C3 to creating two listings L1 and L2.
6. P1 verifies the listing information is correct on listing details page.
P1 exits the application.

#### 2. Customer: Create customer profile
1. Customer C1 signs up for the first time and creates a customer profile.
2. C1 updates preferred games, profile avatar, and region.

#### 3. Customer: Browse listings & manage wishlist
1. Customer C2 signs up and creates a customer profile.
2. C2 browses available listings, including L1 and L2 created by P1.
3. C2 views listing details for L1.
4. C2 adds listing L1 to their wishlist.
5. C2 views their wishlist to confirm the addition.
C2 exits the application.

#### 4. Customer: Create trade offer
1. C1 logs in and views listing L1 owned by provider P1.
2. C1 submits a trade offer T1 by entering offered card details and optional message.
3. System records T1 with status PENDING and displays it in C1’s “My Offers”.
C1 exits the application.

#### 5. Provider: Review and respond to trade offers
1. P1 logs in and views incoming trade offers for listings L1 and L2.
2. P1 messages C1 about trade offer T1.
3. P1 opens offer T1 submitted by C1.
4. P1 chooses one response path:
   - Accept → T1 becomes ACCEPTED and L1 becomes unavailable.
   - Decline → T1 becomes DECLINED.
   - Counteroffer → T1 becomes COUNTERED with a new value.
5. If offer becomes ACCEPTED and trade as been made, P1 can mark offer as COMPLETED.
P1 exits the application.

#### 6. Customer: View, respond to, or withdraw offers & Write review
1. C1 logs in and views all past offers, including statuses ACCEPTED, DECLINED, COUNTERED.
2. For counteroffer T1, C1 may:
   - Accept counter → status becomes ACCEPTED.
   - Reject counter → status becomes REJECTED.
3. For COMPLETED offer, C1 writes a review R1 for P1.
4. C1 creates new offer T2 and later withdraws it → status becomes WITHDRAWN.
C1 exits the application.

#### 7. Customer: Modify customer profile
1. C2 logs in and opens “My Profile”.
2. C2 modifies profile fields such as avatar, contact details, and preferred game.
3. C2 saves the updated profile and verifies changes on the dashboard.
C2 exits the application

#### 8. Provider: Modify provider profile & listings, Respond to Review
1. P1 logs in and opens “My Listings”.
2. P1 edits listing L2 (edit price, grading, condition, etc.) and saves changes.
3. P1 navigates to their reviews and responds to review R1 from P1.
4. P1 edits provider profile information (bio, background image, username).
5. P1 confirms updated details appear correctly.
P1 exits the application.
