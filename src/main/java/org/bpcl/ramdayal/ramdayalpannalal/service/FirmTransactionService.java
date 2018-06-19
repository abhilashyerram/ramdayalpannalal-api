package org.bpcl.ramdayal.ramdayalpannalal.service;

import java.util.Date;

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
	
	public Iterable<FirmTransaction> getFirmTransactionsByFirmIdTransactionDate(long firmId) {
		return firmTransactionRepository.findAll();
	}

	public FirmTransaction addFirmTransaction(long firmId, FirmTransaction firmTransaction) {
	
		firmRepository.findById(firmId);
		
		firmTransactionRepository.save(firmTransaction);
	}

}
