package com.assignment.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.exception.StockException;
import com.assignment.model.StockItem;
import com.assignment.model.StockMovement;
import com.assignment.repository.StockMovementRepo;
import com.assignment.repository.StockRepo;
import com.assignment.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepo stockRepo;

	@Autowired
	private StockMovementRepo stockMovementRepo;

	@Override
	public StockItem addItem(StockItem stockItem) {
		return stockRepo.save(stockItem);
	}

	@Override
	public StockItem updateItem(Long id, int quantity) throws StockException {
		StockItem stockItem = stockRepo.findById(id)
				.orElseThrow(() -> new StockException("Stock item not found with id " + id));
		stockItem.setQuantity(quantity);
		return stockRepo.save(stockItem);
	}

	@Override
	public void deleteItem(long id) throws StockException {
		StockItem stockItem = stockRepo.findById(id)
				.orElseThrow(() -> new StockException("Stock item not found with id " + id));
		stockRepo.delete(stockItem);
	}

	@Override
	public List<StockItem> items() throws StockException {
		List<StockItem> items = stockRepo.findAll();
		if (items.isEmpty())
			throw new StockException("items not found");

		return items;
	}

	@Override
	public StockMovement addStockMovement(StockMovement stockMovement, Long stockItemId) throws StockException {
		StockItem stockItem = stockRepo.findById(stockItemId)
				.orElseThrow(() -> new StockException("Stock item not found with id " + stockItemId));
		
		if (stockItem.getQuantity() <= stockMovement.getQuantity()) {
			throw new StockException("Insufficient stock quantity");
		}
		stockItem.setQuantity(stockItem.getQuantity() - stockMovement.getQuantity());
		stockMovement.setStockItem(stockItem);
		stockMovement.setTimestamp(LocalDateTime.now());
		return stockMovementRepo.save(stockMovement);
	}


	@Override
	public StockItem getItem(long id) throws StockException {
		return stockRepo.findById(id).orElseThrow(() -> new StockException("Stock item not found with id " + id));
	}

	@Override
	public List<StockMovement> stockMovements() throws StockException {
		List<StockMovement> items = stockMovementRepo.findAll();
		if (items.isEmpty())
			throw new StockException("items not found");

		return items;
	}

}
