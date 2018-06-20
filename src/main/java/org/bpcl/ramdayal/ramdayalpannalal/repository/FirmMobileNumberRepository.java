package org.bpcl.ramdayal.ramdayalpannalal.repository;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmMobileNumber;
import org.springframework.data.repository.CrudRepository;

public interface FirmMobileNumberRepository extends CrudRepository<FirmMobileNumber, Long> {

	public Iterable<FirmMobileNumber> findByFirmId(long firmId);
}
