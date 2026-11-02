package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MovieTicket;

@Repository
public interface MovieTicketRepository
        extends JpaRepository<MovieTicket, Long> {

}
