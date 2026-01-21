package com.server.booking.api;

import com.server.booking.application.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve booking by id")
    @ApiResponse(responseCode = "200", description = "Booking found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingDTO.class)))
    public BookingDTO getBookingById(
            @Parameter(description = "ID of booking to retrieve", example = "1")
            @PathVariable int id) {
        return bookingService.getBooking(id);
    }

    @GetMapping("/specialist/{specialistId}")
    @Operation(summary = "Retrieve bookings by specialist Id")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all specialist bookings",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingDTO.class)))
    public List<BookingDTO> getBookingBySpecialistId(
            @Parameter(description = "ID of specialist", example = "1")
            @PathVariable int specialistId) {
        return bookingService.getBookingsBySpecialistId(specialistId);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Retrieve bookings by client Id")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all client bookings",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingDTO.class)))
    public List<BookingDTO> getBookingByClientId(
            @Parameter(description = "ID of client", example = "1")
            @PathVariable int clientId) {
        return bookingService.getBookingsByClientId(clientId);
    }

    @GetMapping("/")
    @Operation(summary = "Retrieve all bookings")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all bookings",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingDTO.class)))
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping
    @Operation(summary = "Create a new booking")
    @ApiResponse(responseCode = "201", description = "Booking created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingDTO.class)))
    public int createBooking(@Valid @RequestBody CreateBookingCommand command) {
        return bookingService.createBooking(command);
    }

/*
    @PatchMapping("/{id}/confirm")
    @Operation(summary = "Confirm a booking")
    @ApiResponse(responseCode = "201", description = "Booking confirmed successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingDTO.class)))
    public int confirmBooking(@PathVariable int id) {
        return bookingService.confirmBySpecialist(id,  currentSpecialistId());
    }

    // Client
    @PatchMapping("/{id}/cancel/client")
    public void cancelBookingByClient(@PathVariable int id) throws AccessDeniedException {
        bookingService.cancelBookingByClient(id, currentUserId());
    }

    // Specialist
    @PatchMapping("/{id}/cancel/specialist")
    public void cancelBookingBySpecialist(@PathVariable int id) {
        bookingService.cancelBookingBySpecialist(id, currentSpecialistId());
    }

    private int currentUserId() {
        return SecurityUtils.currentUserId();
    }

 */

}

