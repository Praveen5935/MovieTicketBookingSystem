package com.example.demo.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.MovieTicketPatchRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MovieTicket;
import com.example.demo.repository.MovieTicketRepository;


@Service
@Transactional
public class MovieTicketServiceImpl implements MovieTicketService {

    private final MovieTicketRepository movieTicketRepository;

    // Manual constructor
    public MovieTicketServiceImpl(
            MovieTicketRepository movieTicketRepository) {

        this.movieTicketRepository = movieTicketRepository;
    }

    // GET all tickets
    @Override
    @Transactional(readOnly = true)
    public List<MovieTicket> getAllTickets() {

        return movieTicketRepository.findAll();
    }

    // GET ticket by ID
    @Override
    @Transactional(readOnly = true)
    public MovieTicket getTicketById(Long ticketId) {

        return movieTicketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ticket not found with ID: " + ticketId));
    }

    // POST: Create ticket
    @Override
    public MovieTicket createTicket(MovieTicket movieTicket) {

        movieTicket.setTicketId(null);

        return movieTicketRepository.save(movieTicket);
    }

    // PUT: Update complete ticket
    @Override
    public MovieTicket updateTicket(
            Long ticketId,
            MovieTicket updatedTicket) {

        MovieTicket existingTicket =
                getTicketById(ticketId);

        existingTicket.setMovieName(
                updatedTicket.getMovieName());

        existingTicket.setCustomerName(
                updatedTicket.getCustomerName());

        existingTicket.setSeatNumber(
                updatedTicket.getSeatNumber());

        existingTicket.setTicketPrice(
                updatedTicket.getTicketPrice());

        return movieTicketRepository.save(existingTicket);
    }

    // PATCH: Partial update
    @Override
    public MovieTicket patchTicket(
            Long ticketId,
            MovieTicketPatchRequest patchRequest) {

        MovieTicket existingTicket =
                getTicketById(ticketId);

        if (patchRequest.getMovieName() != null) {
            existingTicket.setMovieName(
                    patchRequest.getMovieName());
        }

        if (patchRequest.getCustomerName() != null) {
            existingTicket.setCustomerName(
                    patchRequest.getCustomerName());
        }

        if (patchRequest.getSeatNumber() != null) {
            existingTicket.setSeatNumber(
                    patchRequest.getSeatNumber());
        }

        if (patchRequest.getTicketPrice() != null) {
            existingTicket.setTicketPrice(
                    patchRequest.getTicketPrice());
        }

        return movieTicketRepository.save(existingTicket);
    }

    // DELETE ticket
    @Override
    public void deleteTicket(Long ticketId) {

        MovieTicket existingTicket =
                getTicketById(ticketId);

        movieTicketRepository.delete(existingTicket);
    }

    // Bulk insert
    @Override
    public List<MovieTicket> createMultipleTickets(
            List<MovieTicket> tickets) {

        tickets.forEach(ticket -> ticket.setTicketId(null));

        return movieTicketRepository.saveAll(tickets);
    }
}