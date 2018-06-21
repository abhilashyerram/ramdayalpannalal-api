package org.bpcl.ramdayal.ramdayalpannalal.repository;

import java.util.Date;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FirmTransactionRepository extends CrudRepository<FirmTransaction, Long>{

	public Iterable<FirmTransaction> findByFirmId(long firmId);
	
	@Query(value="select * from FIRM_TRANSACTION a where a.FIRM_ID = ?1 and a.TRANSACTION_DATE >= ?2 and a.TRANSACTION_DATE <= ?3 order by a.TRANSACTION_DATE", nativeQuery = true)
	public Iterable<FirmTransaction> findByTransactionDate(long firmId, Date startdate, Date endDate);
	
}
