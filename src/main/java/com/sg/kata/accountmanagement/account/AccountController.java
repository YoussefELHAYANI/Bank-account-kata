package com.sg.kata.accountmanagement.account;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sg.kata.accountmanagement.account.operations.Operation;
import com.sg.kata.accountmanagement.account.operations.OperationDTO;

import javassist.tools.rmi.ObjectNotFoundException;

/**
 * 
 * account controller expose 5 endpoint
 * 
 * POST /retrieve
 * POST /create 
 * GET /allOperation
 * GET /allAcount 
 * GET /CheckAccount​/{id}
 * 
 * @author Youssef
 *
 */
@RestController
@CrossOrigin
public class AccountController {

	
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	AccountService accountServiceImpl;
	
	public AccountController(AccountService accountServiceImpl) {
		super();
		this.accountServiceImpl = accountServiceImpl;
	}
	

	@PostMapping("/save")
	public String saveMoney(@RequestBody OperationDTO deposit) throws ObjectNotFoundException {
		LOGGER.info("operation save money call ");
		accountServiceImpl.saveMoney(deposit);
		return "Operation Done ! your bank account is fed by " + deposit.getAmount();
	}
	@PostMapping("/create")
	public String  createAccount(@RequestBody AccountDTO account) {
		LOGGER.info("operation create Account call ");
		accountServiceImpl.createAccount(account);
		return "Account Created";
	}
	@PostMapping("/retrieve")
	public String retrieveSaving( @RequestBody OperationDTO withdrawal) throws ObjectNotFoundException {
		LOGGER.info("operation retrieve saving call ");
		accountServiceImpl.retrieveSaving(withdrawal);
		return "Operation Done ! you have retreived " + withdrawal.getAmount();
	}
	@GetMapping("/CheckAccount/{id}")
	public Set<Operation> checkOperations(@PathVariable("id") String id) throws ObjectNotFoundException {
		return accountServiceImpl.checkOperations(id);
	}
	@GetMapping("/allAcount")
	public List<Account> getAllAccount(){
		return accountServiceImpl.getAllAccount();
	}
	@GetMapping("/allOperation")
	public List<Operation> getAllOperations(){
		return accountServiceImpl.getAllOperation();
	}
}
