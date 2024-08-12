package com.shopease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.serviceImpl.DatabaseService;

@RestController
public class DatabaseController {
	
	@Autowired
	private DatabaseService databaseService;
	
	@DeleteMapping("/droptable")
	public void deleteTable(@RequestParam String tableName, @RequestParam String constraint) {
		databaseService.dropWithForeignKey(tableName, constraint);
	}

}
