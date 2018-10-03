package com.abcinsurance.abcInsurance.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.abcinsurance.abcInsurance.entities.RiskItem;

/**
 * 
 * @author Pieterv
 *
 */
public interface RiskItemRepository extends JpaRepository<RiskItem, String> {

	List<RiskItem> findByPolicyId(@Param(value = "policyId") String policyId);

}