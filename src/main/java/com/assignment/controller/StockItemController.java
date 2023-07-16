package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.exception.StockException;
import com.assignment.model.StockItem;
import com.assignment.model.StockMovement;
import com.assignment.service.StockService;

@RestController
public class StockItemController {
	
	@Autowired
	private StockService stockService;
	
	@PostMapping("/add")
	public ResponseEntity<StockItem> create(@RequestBody StockItem stockItem){
		return new ResponseEntity<>(stockService.addItem(stockItem),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}/{q}/update")
	public ResponseEntity<StockItem> update(@PathVariable Long id,@PathVariable int q) throws StockException{
		return new ResponseEntity<>(stockService.updateItem(id, q),HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/stocks")
	public ResponseEntity<List<StockItem>> getAllStocks() throws StockException{
		return new ResponseEntity<List<StockItem>>(stockService.items(),HttpStatus.OK);
	}
	
	@DeleteMapping("{id}/delete")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws StockException{
		stockService.deleteItem(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/addStockMovement/{id}")
	public ResponseEntity<StockMovement> add(@RequestBody StockMovement stockMovement,@PathVariable Long id) throws StockException{
		return new ResponseEntity<>(stockService.addStockMovement(stockMovement,id),HttpStatus.CREATED);
	}
	
	@GetMapping("/stocksMovements")
	public ResponseEntity<List<StockMovement>> getAllStocksMovements() throws StockException{
		return new ResponseEntity<List<StockMovement>>(stockService.stockMovements(),HttpStatus.OK);
	}
	

}
