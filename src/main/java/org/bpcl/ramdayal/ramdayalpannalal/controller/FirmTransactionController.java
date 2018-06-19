package org.bpcl.ramdayal.ramdayalpannalal.controller;

import java.util.Date;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmTransaction;
import org.bpcl.ramdayal.ramdayalpannalal.service.FirmTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirmTransactionController {

	@Autowired
	private FirmTransactionService firmTransactionService;

	@GetMapping("/firms/{firmId}/firmTransactions")
	public Iterable<FirmTransaction> getFirmTransactions(@PathVariable long firmId) {
		return firmTransactionService.getFirmTransactionsByFirmIdTransactionDate(firmId);
	}
	
	@PostMapping("/firms/{firmId}/firmTransactions")
	public FirmTransaction addFirmTransaction(@PathVariable long firmId, @RequestBody FirmTransaction firmTransaction) {
		return firmTransactionService.addFirmTransaction(firmId, firmTransaction);
	}
	
}
