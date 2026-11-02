package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieTicketPatchRequest {

    @NotBlank(message = "Movie name cannot be empty")
    @Size(max = 100, message = "Movie name cannot exceed 100 characters")
    private String movieName;

    @NotBlank(message = "Customer name cannot be empty")
    @Size(max = 100, message = "Customer name cannot exceed 100 characters")
    private String customerName;

    @NotBlank(message = "Seat number cannot be empty")
    @Size(max = 10, message = "Seat number cannot exceed 10 characters")
    private String seatNumber;

    @Positive(message = "Ticket price must be positive")
    private Double ticketPrice;
}
