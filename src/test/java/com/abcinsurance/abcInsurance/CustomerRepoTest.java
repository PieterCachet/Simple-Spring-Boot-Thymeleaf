package com.abcinsurance.abcInsurance;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.abcinsurance.abcInsurance.entities.Customer;
import com.abcinsurance.abcInsurance.repos.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = { "default" })
public class CustomerRepoTest {

	private CustomerRepository custRepo;

	@Test
	public void customerCreationTest() {
		String firstName = "Pieter", surname = "Cachet", zaId = "8905088888888";

		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setSurname(surname);
		customer.setZaId(zaId);
		String id = custRepo.save(customer).getId();

		assertNotNull(id);

		customer = custRepo.findById(id).get();
		assertNotNull(customer);
		assertEquals(firstName, customer.getFirstName());
		assertEquals(surname, customer.getSurname());
		assertEquals(zaId, customer.getZaId());
		assertEquals(id, customer.getId());
		assertEquals("08 May 1989", customer.getDateOfBirth());

		for (int i = 0; i < 9; i++) {
			customer = new Customer();
			customer.setFirstName("Name " + i);
			customer.setSurname("Surname " + i);
			customer.setZaId("890508888880" + i);
			custRepo.save(customer);
		}

		for (Customer c : custRepo.findAll()) {
			assertNotNull(c);
			assertNotNull(c.getId());
			assertNotNull(c.getFirstName());
			assertNotNull(c.getSurname());
			assertNotNull(c.getZaId());
			assertNotNull(c.getDateOfBirth());
		}

		final Customer custr = new Customer();
		custr.setFirstName(firstName);
		custr.setSurname(surname);
		custr.setZaId(zaId);

		assertThatThrownBy(() -> custRepo.save(custr)).isInstanceOf(Throwable.class);
	}

	@Autowired
	public void setCustRepo(CustomerRepository custRepo) {
		this.custRepo = custRepo;
	}

}
