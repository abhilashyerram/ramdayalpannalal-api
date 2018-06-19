package org.bpcl.ramdayal.ramdayalpannalal.repository;

import java.util.Date;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FirmTransactionRepository extends CrudRepository<FirmTransaction, Long>{

	public Iterable<FirmTransaction> findByFirmFirmId(long firmId);
	
	@Query("select a from FirmTransaction a where a.firm = :firmId and a.transactionDate between :startDate and :endDate")
	public Iterable<FirmTransaction> findByTransactionDates(@Param("firmId") long firmId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
