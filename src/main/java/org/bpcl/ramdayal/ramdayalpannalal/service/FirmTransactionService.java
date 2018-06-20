package org.bpcl.ramdayal.ramdayalpannalal.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.dto.FirmTransactionDTO;
import org.bpcl.ramdayal.ramdayalpannalal.entity.Firm;
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
	
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd");
	
	public Iterable<FirmTransaction> getFirmTransactionsByFirm(long firmId) {
		return firmTransactionRepository.findByFirmId(firmId);
	}

	public void addFirmTransaction(long firmId, FirmTransactionDTO firmTransactionDto) throws ParseException {
		Optional<Firm> firmProfile =  firmRepository.findById(firmId);
		if(!firmProfile.isPresent()) {
			throw new NullPointerException("No firm is present with the firm id passed");
		}
		else {
			
			FirmTransaction firmTransaction  = new  FirmTransaction();
			
			firmTransaction.setBillNumber(firmTransactionDto.getBillNumber());
			firmTransaction.setNarration(firmTransactionDto.getNarration());
			firmTransaction.setProductPrice(firmTransactionDto.getProductPrice());
			firmTransaction.setQuantity(firmTransactionDto.getQuantity());
			firmTransaction.setTotalPrice(firmTransactionDto.getTotalPrice());
			firmTransaction.setTransactionType(firmTransactionDto.getTransactionType());
			firmTransaction.setTransactionDate(sd.parse(firmTransactionDto.getTransactionDate()));
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

	public Iterable<FirmTransaction> getFirmTransactionsByDates(long firmId, String startDate, String endDate) throws ParseException {
		
		Date stDt = sd.parse(startDate);
		Date endDt = sd.parse(endDate);
		
		return firmTransactionRepository.findByTransactionDate(firmId,stDt, endDt);
	}

}
