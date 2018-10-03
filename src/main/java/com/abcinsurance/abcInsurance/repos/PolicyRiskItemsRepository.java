package com.abcinsurance.abcInsurance.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcinsurance.abcInsurance.entities.PolicyRiskItems;

/**
 * 
 * @author Pieterv
 *
 */
public interface PolicyRiskItemsRepository extends JpaRepository<PolicyRiskItems, String> {

}
