package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.model.StockMovement;

public interface StockMovementRepo extends JpaRepository<StockMovement, Long> {

}
