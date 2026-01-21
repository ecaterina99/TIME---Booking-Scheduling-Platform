package com.server.schedule.api;

import com.server.booking.application.BookingDTO;
import com.server.booking.application.CreateBookingCommand;
import com.server.schedule.application.CreateScheduleCommand;
import com.server.schedule.application.ScheduleDTO;
import com.server.schedule.application.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{specialistId}")
    @Operation(summary = "Retrieve schedule by specialist id")
    @ApiResponse(responseCode = "200", description = "Schedule found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ScheduleDTO.class)))
    public ScheduleDTO getScheduleBySpecialistId(
            @Parameter(description = "ID of specialist", example = "1")
            @PathVariable int specialistId) {
        return scheduleService.findBySpecialistId(specialistId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Schedule created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ScheduleDTO.class)))
    public void createSchedule(@Valid @RequestBody CreateScheduleCommand command) {
        scheduleService.createOrUpdateSchedule(command);
    }

}
