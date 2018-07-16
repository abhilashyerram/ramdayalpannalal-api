package org.bpcl.ramdayal.ramdayalpannalal.repository;

import java.util.Date;
import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FirmTransactionRepository extends CrudRepository<FirmTransaction, Long>{

	Iterable<FirmTransaction> findByFirmId(long firmId);
	
	@Query(value="select * from FIRM_TRANSACTION a where a.FIRM_ID = ?1 and a.TRANSACTION_DATE >= ?2 and a.TRANSACTION_DATE <= ?3 order by a.TRANSACTION_DATE ", nativeQuery = true)
    Iterable<FirmTransaction> findByTransactionDate(long firmId, Date startdate, Date endDate);
	
	@Query(value="select sum(amount) from FIRM_TRANSACTION a where a.FIRM_ID = ?1 and  upper(TRANSACTION_TYPE) = 'CREDIT' ", nativeQuery = true)
    Optional<Double> findSumByTransactionDateTransactionTypeCredit(long firmId);	
	
	@Query(value="select sum(amount) from FIRM_TRANSACTION a where a.FIRM_ID = ?1 and  upper(TRANSACTION_TYPE) = 'DEBIT' ", nativeQuery = true)
    Optional<Double> findSumByTransactionDateTransactionTypeDebit(long firmId);
}
