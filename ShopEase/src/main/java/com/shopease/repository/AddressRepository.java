package com.shopease.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopease.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

	@Query("SELECT a FROM Address a WHERE a.country=:country AND a.state=:state AND a.city=:city AND a.street=:street AND a.pinCode=:pincode")
	Optional<Address> findAddress(@Param("country") String country, @Param("state") String state, @Param("city") String city, @Param("street") String street, @Param("pincode") String pincode);
}
