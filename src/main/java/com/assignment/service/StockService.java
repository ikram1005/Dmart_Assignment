package com.assignment.service;

import java.util.List;

import com.assignment.exception.StockException;
import com.assignment.model.StockItem;
import com.assignment.model.StockMovement;

public interface StockService {

	StockItem addItem(StockItem stockItem);
	StockItem updateItem(Long id,int quantity)throws StockException;
	void deleteItem(long id)throws StockException;
	List<StockItem> items()throws StockException;
	StockItem getItem(long id)throws StockException;
	
	StockMovement addStockMovement(StockMovement stockMovement,Long stockItemId) throws StockException;
	List<StockMovement> stockMovements()throws StockException;
}
