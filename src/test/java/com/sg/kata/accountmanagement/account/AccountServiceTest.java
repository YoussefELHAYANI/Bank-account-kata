package com.sg.kata.accountmanagement.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.sg.kata.accountmanagement.account.operations.Operation;
import com.sg.kata.accountmanagement.account.operations.OperationDTO;
import com.sg.kata.accountmanagement.account.operations.OperationRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@SpringBootTest
public class AccountServiceTest {
	
	@Mock
	OperationRepository operationRepository;
	@Mock
	AccountRepository ownerRepository;
	@Mock
	ModelMapper modelMapper;
	@InjectMocks
	AccountServiceImpl accountService;
	
	String id = UUID.randomUUID().toString();
	String ownerId = UUID.randomUUID().toString();

	@Rule
	  public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void checkOperations() throws ObjectNotFoundException {
		Account account = new Account();
		account.setOperation(new HashSet<Operation>());
		Optional<Account> found = Optional.of(account);
		Mockito.when(ownerRepository.findById(id)).thenReturn(found);
		assertNotNull(accountService.checkOperations(id));
	}
	@Test
	public void checkOperationsException(){
		try {
			accountService.checkOperations("random");
		} catch (ObjectNotFoundException e) {
			exception.expect(ObjectNotFoundException.class);
			e.printStackTrace();
		}
	}
	@Test
	public void saveMoney() throws ObjectNotFoundException {
		OperationDTO deposit = new OperationDTO();
		deposit.setOwnerId(ownerId);
		Operation operation = new Operation();
		operation.setAmount(new BigDecimal(10000));
		
		Account account = new Account();
		account.setSold(new BigDecimal(30000));
		account.setOperation(new HashSet<Operation>());
		Optional<Account> found = Optional.of(account);
		//when
		Mockito.when(ownerRepository.findById(deposit.getOwnerId())).thenReturn(found);
		Mockito.when(modelMapper.map(deposit, Operation.class)).thenReturn(operation);
		accountService.saveMoney(deposit);
		
		assertNotNull(ownerRepository.findById(ownerId).get().getOperation());
		assertEquals(account.getSold(),new BigDecimal(40000));
		
	}
	@Test
	public void retrieveSaving() throws ObjectNotFoundException {
		OperationDTO withdrawal = new OperationDTO();
		withdrawal.setOwnerId(ownerId);
		Operation operation = new Operation();
		operation.setAmount(new BigDecimal(10000));
		
		Account account = new Account();
		account.setSold(new BigDecimal(30000));
		account.setOperation(new HashSet<Operation>());
		Optional<Account> found = Optional.of(account);
		// when
		Mockito.when(ownerRepository.findById(withdrawal.getOwnerId())).thenReturn(found);
		Mockito.when(modelMapper.map(withdrawal, Operation.class)).thenReturn(operation);		
		accountService.retrieveSaving(withdrawal);
		
		assertNotNull(ownerRepository.findById(ownerId).get().getOperation());
		assertEquals(account.getSold(),new BigDecimal(20000));
		
	}

}
