# Architecture
## Overview

The Service Booking Platform consists of two applications:

-booking-api — a Spring Boot REST API that contains the core business logic and implements DDD boundaries internally.

-booking-web — a separate web client (SPA) that interacts with the REST API.

The main focus of the project is a strong backend with clear domain boundaries, consistent business rules, and event-driven side effects using Kafka.

# Backend Architecture (booking-api)

## Internal Bounded Contexts

The backend contains the following bounded contexts:

-Booking (Core)

-Scheduling

-Organization

-Notification

## Layering Rules (Inside Each Context)

## Domain Layer

aggregates, entities, value objects

domain services

domain events

business rules and invariants

No frameworks or technical concerns.

## Application Layer

use cases (create booking, cancel booking, reschedule booking)

orchestration and transaction boundaries

calls domain logic + repositories

## Infrastructure Layer

persistence (JPA/Hibernate repositories)

Kafka producers/consumers

configuration and external adapters

## API Layer

REST controllers

DTOs

validation

thin controllers delegating to application layer

# Web Client Architecture (booking-web)

The web client is a separate application that:

-calls the REST API

-handles UI, routing, and state management

-does not contain business rules that must be consistent (those live in the backend)

### Web Client → REST API

-synchronous HTTP calls

-DTO-based contract

-OpenAPI/Swagger used for API documentation


### Internal Asynchronous Communication (Kafka)

Kafka is used for:

-notifications 

-audit logging

-reminders / hold expiration processing
