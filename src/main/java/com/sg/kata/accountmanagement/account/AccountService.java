package com.sg.kata.accountmanagement.account;

import java.util.List;
import java.util.Set;

import com.sg.kata.accountmanagement.operations.Operation;
import com.sg.kata.accountmanagement.operations.OperationDTO;

import javassist.tools.rmi.ObjectNotFoundException;

public interface AccountService {
	public void saveMoney(OperationDTO deposit) throws ObjectNotFoundException;
	public void retrieveSaving(OperationDTO withdrawal) throws ObjectNotFoundException;
	public Set<Operation> checkOperations(String id) throws ObjectNotFoundException;
	public void createOwner(OwnerDTO owner);
	public List<Owner> getAllAccount();
	public List<Operation> getAllOperation();
}
