package com.sg.kata.accountmanagement.account;

import java.util.List;
import java.util.Set;

import com.sg.kata.accountmanagement.account.operations.Operation;
import com.sg.kata.accountmanagement.account.operations.OperationDTO;

import javassist.tools.rmi.ObjectNotFoundException;

public interface AccountService {
	public void saveMoney(OperationDTO deposit) throws ObjectNotFoundException;
	public void retrieveSaving(OperationDTO withdrawal) throws ObjectNotFoundException;
	public Set<Operation> checkOperations(String id) throws ObjectNotFoundException;
	public void createAccount(AccountDTO owner);
	public List<Account> getAllAccount();
	public List<Operation> getAllOperation();
}
