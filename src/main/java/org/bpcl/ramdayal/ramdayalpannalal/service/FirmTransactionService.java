package org.bpcl.ramdayal.ramdayalpannalal.service;

import org.bpcl.ramdayal.ramdayalpannalal.dto.FirmTransactionDTO;
import org.bpcl.ramdayal.ramdayalpannalal.dto.FirmTransactionsDTO;
import org.bpcl.ramdayal.ramdayalpannalal.entity.Firm;
import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmTransaction;
import org.bpcl.ramdayal.ramdayalpannalal.repository.FirmRepository;
import org.bpcl.ramdayal.ramdayalpannalal.repository.FirmTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class FirmTransactionService {

	@Autowired
	private FirmTransactionRepository firmTransactionRepository;
	@Autowired
	private FirmRepository firmRepository;
	
	private final SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
	public Iterable<FirmTransaction> getFirmTransactionsByFirm(long firmId) {
		return firmTransactionRepository.findByFirmId(firmId);
	}

	public void saveFirmTransaction(long firmId, FirmTransactionDTO firmTransactionDto) throws ParseException {
		Optional<Firm> firmProfile =  firmRepository.findById(firmId);
		if(!firmProfile.isPresent()) {
			throw new NullPointerException("No firm is present with the firm id passed");
		}
		else {
			FirmTransaction firmTransaction = setFirmTransaction(firmTransactionDto, firmProfile);
			firmTransactionRepository.save(firmTransaction);
		}
	}

	private FirmTransaction setFirmTransaction(FirmTransactionDTO firmTransactionDto, Optional<Firm> firmProfile)
			throws ParseException {
		FirmTransaction firmTransaction  = new  FirmTransaction();
		
		firmTransaction.setBillNumber(firmTransactionDto.getBillNumber());
		firmTransaction.setNarration(firmTransactionDto.getNarration());
		firmTransaction.setProductPrice(firmTransactionDto.getProductPrice());
		firmTransaction.setQuantity(firmTransactionDto.getQuantity());
		firmTransaction.setAmount(firmTransactionDto.getAmount());
		firmTransaction.setTransactionType(firmTransactionDto.getTransactionType());
		firmTransaction.setTransactionDate(sd.parse(firmTransactionDto.getTransactionDate()));
		firmTransaction.setFirm(firmProfile.get());
		return firmTransaction;
	}

	public void updateFirmTransaction(long firmTransactionId, FirmTransaction firmTransaction) {
		firmTransaction.setId(firmTransactionId);
		firmTransactionRepository.save(firmTransaction);
	}

	public void deleteFirmTransaction(long firmTransactionId) {
		firmTransactionRepository.deleteById(firmTransactionId);
	}

	public FirmTransactionsDTO getFirmTransactionsByDates(long firmId, String startDate, String endDate) throws ParseException {
		Date stDt = sd.parse(startDate);
		Date endDt = sd.parse(endDate);
		FirmTransactionsDTO firmTransactions = new FirmTransactionsDTO();
		Optional<Double> creditsSum = firmTransactionRepository.findSumByTransactionDateTransactionTypeCredit(firmId);
		Optional<Double> debitsSum = firmTransactionRepository.findSumByTransactionDateTransactionTypeDebit(firmId);
		
		if(!debitsSum.isPresent() || !creditsSum.isPresent() ) {
			if(!debitsSum.isPresent() && creditsSum.isPresent()) {
				firmTransactions.setOpeningBalance(creditsSum.get());	
			}
			else if(!creditsSum.isPresent() && debitsSum.isPresent()) {
				firmTransactions.setOpeningBalance(0 - debitsSum.get());
			}
		}
		else {
			firmTransactions.setOpeningBalance(creditsSum.get() - debitsSum.get());
		}
		firmTransactions.setFirmTransactions(firmTransactionRepository.findByTransactionDate(firmId,stDt, endDt));
		
		return firmTransactions;
	}

}
