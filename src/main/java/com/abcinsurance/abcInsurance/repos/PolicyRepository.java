package com.abcinsurance.abcInsurance.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.abcinsurance.abcInsurance.entities.Policy;

/**
 * 
 * @author Pieterv
 *
 */
public interface PolicyRepository extends JpaRepository<Policy, String> {

	Policy findByCustomerId(@Param(value = "customerId") String customerId);

}
