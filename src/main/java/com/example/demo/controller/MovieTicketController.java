package com.example.demo.controller;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.MovieTicketPatchRequest;
import com.example.demo.model.MovieTicket;
import com.example.demo.service.MovieTicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@Validated
public class MovieTicketController {

    private final MovieTicketService movieTicketService;

    // Manual constructor
    public MovieTicketController(
            MovieTicketService movieTicketService) {

        this.movieTicketService = movieTicketService;
    }

    // GET all tickets
    @GetMapping
    public ResponseEntity<List<MovieTicket>> getAllTickets() {

        return ResponseEntity.ok(
                movieTicketService.getAllTickets());
    }

    // GET ticket by ID
    @GetMapping("/{ticketId}")
    public ResponseEntity<MovieTicket> getTicketById(
            @PathVariable
            @Positive(message = "Ticket ID must be positive")
            Long ticketId) {

        return ResponseEntity.ok(
                movieTicketService.getTicketById(ticketId));
    }

    // POST: Create ticket
    @PostMapping
    public ResponseEntity<MovieTicket> createTicket(
            @Valid @RequestBody MovieTicket movieTicket) {

        MovieTicket savedTicket =
                movieTicketService.createTicket(movieTicket);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTicket);
    }

    // PUT: Update complete ticket
    @PutMapping("/{ticketId}")
    public ResponseEntity<MovieTicket> updateTicket(
            @PathVariable
            @Positive(message = "Ticket ID must be positive")
            Long ticketId,
            @Valid @RequestBody MovieTicket movieTicket) {

        MovieTicket updatedTicket =
                movieTicketService.updateTicket(
                        ticketId, movieTicket);

        return ResponseEntity.ok(updatedTicket);
    }

    // PATCH: Partial update
    @PatchMapping("/{ticketId}")
    public ResponseEntity<MovieTicket> patchTicket(
            @PathVariable
            @Positive(message = "Ticket ID must be positive")
            Long ticketId,
            @Valid @RequestBody
            MovieTicketPatchRequest patchRequest) {

        MovieTicket updatedTicket =
                movieTicketService.patchTicket(
                        ticketId, patchRequest);

        return ResponseEntity.ok(updatedTicket);
    }

    // DELETE ticket
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<String> deleteTicket(
            @PathVariable
            @Positive(message = "Ticket ID must be positive")
            Long ticketId) {

        movieTicketService.deleteTicket(ticketId);

        return ResponseEntity.ok(
                "Ticket deleted successfully");
    }

    // Bulk insert
    @PostMapping("/bulk")
    public ResponseEntity<List<MovieTicket>> createMultipleTickets(
            @Valid @RequestBody List<@Valid MovieTicket> tickets) {

        List<MovieTicket> savedTickets =
                movieTicketService.createMultipleTickets(tickets);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTickets);
    }
}
