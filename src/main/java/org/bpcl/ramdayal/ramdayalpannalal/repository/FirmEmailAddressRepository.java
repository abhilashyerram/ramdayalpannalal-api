package org.bpcl.ramdayal.ramdayalpannalal.repository;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmEmailAddress;
import org.springframework.data.repository.CrudRepository;

public interface FirmEmailAddressRepository extends CrudRepository<FirmEmailAddress, Long> {

	Iterable<FirmEmailAddress> findByFirmId(long firmId);

}
