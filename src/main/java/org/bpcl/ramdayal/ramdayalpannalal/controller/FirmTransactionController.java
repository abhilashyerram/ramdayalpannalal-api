package org.bpcl.ramdayal.ramdayalpannalal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmTransaction;
import org.bpcl.ramdayal.ramdayalpannalal.service.FirmTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirmTransactionController {

	@Autowired
	private FirmTransactionService firmTransactionService;

	@GetMapping("/firms/{firmId}/firmTransactions")
	public Iterable<FirmTransaction> getFirmTransactions(@PathVariable long firmId) {
		return firmTransactionService.getFirmTransactionsByFirm(firmId);
	}
	
	@GetMapping("/firms/{firmId}/firmTransactions/{startDate}/{endDate}")
	public Iterable<FirmTransaction> getFirmTransactionsByDates(@PathVariable long firmId,@PathVariable String startDate, @PathVariable String endDate) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd");
		Date stDt=null,endDt=null;
		try {
			stDt = sd.parse(startDate);
			endDt = sd.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return firmTransactionService.getFirmTransactionsByDates(firmId,stDt,endDt);
	}
	
	@PostMapping("/firms/{firmId}/firmTransactions")
	public void addFirmTransaction(@PathVariable long firmId, @RequestBody FirmTransaction firmTransaction) {
		firmTransactionService.addFirmTransaction(firmId, firmTransaction);
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
