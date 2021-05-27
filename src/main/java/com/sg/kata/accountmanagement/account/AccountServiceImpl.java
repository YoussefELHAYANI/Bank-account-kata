package com.sg.kata.accountmanagement.account;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sg.kata.accountmanagement.account.operations.Operation;
import com.sg.kata.accountmanagement.account.operations.OperationDTO;
import com.sg.kata.accountmanagement.account.operations.OperationRepository;

import javassist.tools.rmi.ObjectNotFoundException;



@Service
public class AccountServiceImpl implements AccountService {
	
	private static final String DEPOSIT = "deposit";
	private static final String WITHDRAWAL = "withdrawal";
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	OperationRepository operationRepository;
	AccountRepository ownerRepository;
	ModelMapper modelMapper;

	

	public AccountServiceImpl(OperationRepository operationRepository, AccountRepository ownerRepository,
			ModelMapper modelMapper) {
		super();
		this.operationRepository = operationRepository;
		this.ownerRepository = ownerRepository;
		this.modelMapper = modelMapper;
	}



	@Override
	public Set<Operation> checkOperations(String id) throws ObjectNotFoundException {
		Optional<Account> found =ownerRepository.findById(id);
		if(found.isPresent()) {
			Account owner = found.get();
			return owner.getOperation();
		} else {
			throw new ObjectNotFoundException("Unable to found owner with id: " + id);
		}
	}
	


	@Override
	public void saveMoney(OperationDTO deposit) throws ObjectNotFoundException {
		
		Operation depositPersist = modelMapper.map(deposit, Operation.class);
		Optional<Account> found =ownerRepository.findById(deposit.getOwnerId());
		if(found.isPresent()) {
			LOGGER.info("Owner Found");
			depositPersist.setType(DEPOSIT);
			Account owner = found.get();
			owner.setSold(owner.getSold().add(depositPersist.getAmount()));
			depositPersist.setAccount(owner);
			depositPersist.setBalance(owner.getSold());
			operationRepository.save(depositPersist);
		} else {
			throw new ObjectNotFoundException("Unable to found  owner with id: " + deposit.getOwnerId());
		}
	}


	@Override
	public void retrieveSaving(OperationDTO withdrawal) throws ObjectNotFoundException {
		Operation withdrawakPersist = modelMapper.map(withdrawal, Operation.class);
		Optional<Account> found = ownerRepository.findById(withdrawal.getOwnerId());
		if(found.isPresent()) {
			LOGGER.info("Owner Found");
			withdrawakPersist.setType(WITHDRAWAL);
			Account owner = found.get();
			owner.setSold(owner.getSold().add(withdrawakPersist.getAmount().negate()));
			withdrawakPersist.setAccount(owner);
			withdrawakPersist.setBalance(owner.getSold());
			operationRepository.save(withdrawakPersist);
		}else {
			throw new ObjectNotFoundException("Unable to found  owner with id: " + withdrawal.getOwnerId());
		}
		
	}


	@Override
	public void createAccount(AccountDTO owner) {
		Account ownerPersist = modelMapper.map(owner, Account.class);
		ownerRepository.save(ownerPersist);
	}

	@Override
	public List<Account> getAllAccount() {
		return ownerRepository.findAll();
	}

	@Override
	public List<Operation> getAllOperation() {
		return operationRepository.findAll() ;
	}	

}
