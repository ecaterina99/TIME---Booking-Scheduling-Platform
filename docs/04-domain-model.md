# Domain Model

Overview

This document describes the core domain model of the Service Booking Platform.
The model follows Domain-Driven Design (DDD) principles and focuses on expressing business concepts clearly and consistently.

## Core Domain Model (Booking Context)
### Aggregate: Booking

Booking is the main aggregate root and represents a single reservation of a service with a specialist for a specific time range.

### Responsibilities

-control the booking lifecycle

-enforce booking invariants

-manage state transitions

-raise domain events

### Key Attributes

-bookingId

-organizationId

-specialistId

-serviceId

-clientId

-timeRange

-status

-createdAt

### Entity: SlotHold

Represents a temporary reservation of a time slot before booking confirmation.

### Responsibilities

-reserve a slot for a limited time

-expire automatically if not confirmed

### Value Objects

### TimeRange

Represents a time interval.

-startTime

-endTime

startTime must be before endTime, duration must be positive,  overlap detection logic

### BookingStatus

Represents the lifecycle state of a booking.

Possible values:

-HOLD

-CONFIRMED

-CANCELLED

-EXPIRED

### Domain Services

-AvailabilityChecker

Determines whether a given time range is available for a specialist.

-check schedule availability

-check existing bookings

-apply business rules consistently




