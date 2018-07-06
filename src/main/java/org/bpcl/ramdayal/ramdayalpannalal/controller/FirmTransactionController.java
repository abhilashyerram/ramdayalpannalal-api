package org.bpcl.ramdayal.ramdayalpannalal.controller;

import org.bpcl.ramdayal.ramdayalpannalal.dto.FirmTransactionDTO;
import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmTransaction;
import org.bpcl.ramdayal.ramdayalpannalal.service.FirmTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class FirmTransactionController {

	@Autowired
	private FirmTransactionService firmTransactionService;
	
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd");

	@GetMapping("/firms/{firmId}/firmTransactions")
	public Iterable<FirmTransaction> getFirmTransactions(@PathVariable long firmId) {
		return firmTransactionService.getFirmTransactionsByFirm(firmId);
	}
	
	@GetMapping("/firms/{firmId}/firmTransactions/{startDate}/{endDate}")
	public Iterable<FirmTransaction> getFirmTransactionsByDates(@PathVariable long firmId,@PathVariable String startDate, @PathVariable String endDate) throws ParseException {
		return firmTransactionService.getFirmTransactionsByDates(firmId, startDate, endDate);
	}
	
	@PostMapping("/firms/{firmId}/firmTransactions")
	public void addFirmTransaction(@PathVariable long firmId, @RequestBody FirmTransactionDTO firmTransactionDto) throws ParseException {
		firmTransactionService.addFirmTransaction(firmId, firmTransactionDto);
	}
	
	@PutMapping("/firms/{firmId}/firmTransactions/{firmTransactionId}")
	public void updateFirmTransaction(@PathVariable long firmTransactionId, @RequestBody FirmTransaction firmTransaction) {
		firmTransactionService.updateFirmTransaction(firmTransactionId, firmTransaction);
	}
	
	@DeleteMapping("/firms/{firmId}/firmTransactions/{firmTransactionId}")
	public void deleteFirmTransaction(@PathVariable long firmTransactionId) {
		firmTransactionService.deleteFirmTransaction(firmTransactionId);
	}
}
