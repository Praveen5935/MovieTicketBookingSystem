package com.example.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "movie_tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @NotBlank(message = "Movie name is required")
    @Column(nullable = false)
    private String movieName;

    @NotBlank(message = "Customer name cannot be empty")
    @Column(nullable = false)
    private String customerName;

    @NotBlank(message = "Seat number is required")
    @Column(nullable = false)
    private String seatNumber;

    @Positive(message = "Ticket price must be positive")
    @Column(nullable = false)
    private Double ticketPrice;
}
