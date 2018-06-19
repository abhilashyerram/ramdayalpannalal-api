package org.bpcl.ramdayal.ramdayalpannalal.repository;

import java.util.Date;
import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FirmTransactionRepository extends CrudRepository<FirmTransaction, Long>{

	//@Query("select a from Firm_Transaction a where a.firm_id = :firmId and a.transaction_date between :startDate and :endDate")
	//public Optional<FirmTransaction> getFirmTransactionsByFirmIdTransactionDate(long firmId, Date startDate, Date endDate);
}
