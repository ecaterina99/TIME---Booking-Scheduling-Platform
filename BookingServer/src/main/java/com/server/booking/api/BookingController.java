package com.server.booking.api;

import com.server.booking.application.BookingDTO;
import com.server.booking.application.BookingService;
import com.server.booking.application.CreateBookingCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel booking")
    @ApiResponse(responseCode = "204", description = "Booking canceled successfully")
    public void cancelBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
    }
}
