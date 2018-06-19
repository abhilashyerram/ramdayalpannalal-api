package org.bpcl.ramdayal.ramdayalpannalal.service;

import java.util.Date;
import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.dto.TransactionDate;
import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmProfile;
import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmTransaction;
import org.bpcl.ramdayal.ramdayalpannalal.repository.FirmRepository;
import org.bpcl.ramdayal.ramdayalpannalal.repository.FirmTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirmTransactionService {

	@Autowired
	private FirmTransactionRepository firmTransactionRepository;
	@Autowired
	private FirmRepository firmRepository;
	
	public Iterable<FirmTransaction> getFirmTransactionsByFirm(long firmId) {
		return firmTransactionRepository.findByFirmFirmId(firmId);
	}

	public void addFirmTransaction(long firmId, FirmTransaction firmTransaction) {
		Optional<FirmProfile> firmProfile =  firmRepository.findById(firmId);
		if(!firmProfile.isPresent()) {
			throw new NullPointerException("No firm is present with the firm id passed");
		}
		else {
			firmTransaction.setFirm(firmProfile.get());
			firmTransactionRepository.save(firmTransaction);
		}
	}

	public void updateFirmTransaction(long firmTransactionId, FirmTransaction firmTransaction) {
		firmTransaction.setId(firmTransactionId);
		firmTransactionRepository.save(firmTransaction);
	}

	public void deleteFirmTransaction(long firmTransactionId) {
		firmTransactionRepository.deleteById(firmTransactionId);
	}

	public Iterable<FirmTransaction> getFirmTransactionsByDates(long firmId, Date startdate, Date endDate) {
		return firmTransactionRepository.findByTransactionDates(firmId, startdate, endDate);
	}

}
