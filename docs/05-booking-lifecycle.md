# Booking Lifecycle
### Overview

This document describes the lifecycle of a booking in the Service Booking Platform.
It defines valid states, transitions, and the business rules governing each transition.

## Booking States
### HOLD
A temporary state where a time slot is reserved but not yet confirmed.

-a slot hold has a fixed expiration time

-only one active hold is allowed per slot

-expired holds cannot be confirmed

### CONFIRMED
The booking is finalized and confirmed.

-confirmation is allowed only from HOLD state

-availability must still be valid at confirmation time

-booking duration is validated again

### CANCELLED
The booking was cancelled by the client, specialist, or organization administrator.

-cancellation is allowed only for CONFIRMED bookings

-cancellation may be restricted based on time (policy-based)

-cancellation frees the slot

### EXPIRED
The booking or slot hold expired due to timeout.

