package com.shopease.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class DatabaseService {
	
	@Autowired
	private EntityManager entityManager;
	
	public void dropWithForeignKey(String tableName, String foreignKeyConstraint) {
		String dropSql = String.format("ALTER TABLE %s DROP FOREIGN KEY %s", tableName, foreignKeyConstraint);
		Query dropQuery = entityManager.createNativeQuery(dropSql);
		dropQuery.executeUpdate();
		
		String dropSqlTable = String.format("DROP TABLE %s", tableName);
		Query dropQueryTable = entityManager.createNativeQuery(dropSqlTable);
		dropQueryTable.executeUpdate();
	}

}
