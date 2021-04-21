package com.sg.kata.accountmanagement.account;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.kata.accountmanagement.operations.Operation;
import com.sg.kata.accountmanagement.operations.OperationDTO;
import com.sg.kata.accountmanagement.operations.OperationRepository;

import javassist.tools.rmi.ObjectNotFoundException;



@Service
public class AccountServiceImpl implements AccountService {
	
	private static final String DEPOSIT = "deposit";
	private static final String WITHDRAWAL = "withdrawal";
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OperationRepository operationRepository;
	
	@Autowired
	OwnerRepository ownerRepository;
	
	@Autowired
	ModelMapper modelMapper;

	

	@Override
	public Set<Operation> checkOperations(String id) throws ObjectNotFoundException {
		Optional<Owner> found =ownerRepository.findById(id);
		if(found.isPresent()) {
			Owner owner = found.get();
			return owner.getOperation();
		} else {
			throw new ObjectNotFoundException("Unable to found owner with id: " + id);
		}
	}
	


	@Override
	public void saveMoney(OperationDTO deposit) throws ObjectNotFoundException {
		
		Operation depositPersist = modelMapper.map(deposit, Operation.class);
		Optional<Owner> found =ownerRepository.findById(deposit.getOwnerId());
		if(found.isPresent()) {
			LOGGER.info("Owner Found");
			depositPersist.setType(DEPOSIT);
			Owner owner = found.get();
			owner.setSold(owner.getSold().add(depositPersist.getAmount()));
			depositPersist.setOwner(owner);
			depositPersist.setBalance(owner.getSold());
			operationRepository.save(depositPersist);
		} else {
			throw new ObjectNotFoundException("Unable to found  owner with id: " + deposit.getOwnerId());
		}
	}


	@Override
	public void retrieveSaving(OperationDTO withdrawal) throws ObjectNotFoundException {
		Operation depositPersist = modelMapper.map(withdrawal, Operation.class);
		Optional<Owner> found = ownerRepository.findById(withdrawal.getOwnerId());
		if(found.isPresent()) {
			LOGGER.info("Owner Found");
			depositPersist.setType(WITHDRAWAL);
			Owner owner = found.get();
			owner.setSold(owner.getSold().add(depositPersist.getAmount().negate()));
			depositPersist.setOwner(owner);
			depositPersist.setBalance(owner.getSold());
			operationRepository.save(depositPersist);
		}else {
			throw new ObjectNotFoundException("Unable to found  owner with id: " + withdrawal.getOwnerId());
		}
		
	}


	@Override
	public void createOwner(OwnerDTO owner) {
		Owner ownerPersist = modelMapper.map(owner, Owner.class);
		ownerRepository.save(ownerPersist);
	}

	@Override
	public List<Owner> getAllAccount() {
		return ownerRepository.findAll();
	}



	@Override
	public List<Operation> getAllOperation() {
		return operationRepository.findAll() ;
	}	

}
