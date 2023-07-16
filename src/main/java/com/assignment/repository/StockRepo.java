package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.model.StockItem;

public interface StockRepo extends JpaRepository<StockItem,Long>{

}
