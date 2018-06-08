package org.bpcl.ramdayal.ramdayalpannalal.repository;

import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmProfile;
import org.springframework.data.repository.CrudRepository;

public interface FirmRepository extends CrudRepository<FirmProfile, Long> {

	Optional<FirmProfile> findByDisplayNameIgnoreCaseContaining(String firmName);
	
	FirmProfile findOneByDisplayName(String displayName);
}
