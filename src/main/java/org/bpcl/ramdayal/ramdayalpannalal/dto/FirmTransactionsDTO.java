package org.bpcl.ramdayal.ramdayalpannalal.dto;

import java.io.Serializable;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmTransaction;

public class FirmTransactionsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4820774929248098406L;
	
	private double openingBalance;
	private Iterable<FirmTransaction> firmTransactions;

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public Iterable<FirmTransaction> getFirmTransactions() {
		return firmTransactions;
	}

	public void setFirmTransactions(Iterable<FirmTransaction> firmTransactions) {
		this.firmTransactions = firmTransactions;
	}
}
