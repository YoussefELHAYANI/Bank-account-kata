package com.sg.kata.accountmanagement.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OwnerRepository  extends JpaRepository<Owner, String> {

}
