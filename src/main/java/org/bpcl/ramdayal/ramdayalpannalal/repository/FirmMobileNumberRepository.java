package org.bpcl.ramdayal.ramdayalpannalal.repository;

import java.util.Collection;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmMobileNumber;
import org.springframework.data.repository.CrudRepository;

public interface FirmMobileNumberRepository extends CrudRepository<FirmMobileNumber, Long> {

	Collection<? extends FirmMobileNumber> findByFirmFirmId(long firmId);
}
