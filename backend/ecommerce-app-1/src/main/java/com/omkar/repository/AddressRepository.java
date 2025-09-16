package com.omkar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkar.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
