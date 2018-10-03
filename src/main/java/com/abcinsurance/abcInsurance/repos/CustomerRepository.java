package com.abcinsurance.abcInsurance.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcinsurance.abcInsurance.entities.Customer;

/**
 * 
 * @author Pieterv
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
