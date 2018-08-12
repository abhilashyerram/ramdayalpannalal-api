package org.bpcl.ramdayal.ramdayalpannalal.repository;

import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

	Optional<Account> findByUsername(String username);
	
}
