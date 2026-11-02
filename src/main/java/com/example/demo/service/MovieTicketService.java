package com.example.demo.service;
import java.util.List;
import com.example.demo.dto.MovieTicketPatchRequest;
import com.example.demo.model.MovieTicket;

public interface MovieTicketService {

    List<MovieTicket> getAllTickets();

    MovieTicket getTicketById(Long ticketId);

    MovieTicket createTicket(MovieTicket movieTicket);

    MovieTicket updateTicket(
            Long ticketId,
            MovieTicket movieTicket);

    MovieTicket patchTicket(
            Long ticketId,
            MovieTicketPatchRequest patchRequest);

    void deleteTicket(Long ticketId);

    List<MovieTicket> createMultipleTickets(
            List<MovieTicket> tickets);
}
