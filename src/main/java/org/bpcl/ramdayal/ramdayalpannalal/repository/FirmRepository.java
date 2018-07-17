package org.bpcl.ramdayal.ramdayalpannalal.repository;

import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.entity.Firm;
import org.springframework.data.repository.CrudRepository;

public interface FirmRepository extends CrudRepository<Firm, Long> {

	Optional<Firm> findByDisplayNameIgnoreCaseContaining(String firmName);
	
	Firm findOneByDisplayName(String displayName);
	
	Firm findByAccountUsername(String username);
}
