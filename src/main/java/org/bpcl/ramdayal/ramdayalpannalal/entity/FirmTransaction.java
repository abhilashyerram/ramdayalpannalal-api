package org.bpcl.ramdayal.ramdayalpannalal.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name="Firm_Transaction")
public class FirmTransaction extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1972683277470152379L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date transactionDate;
	private String transactionType; //Credit or Debit
	private long billNumber;
	private float productPrice;
	private float quantity; 
	private float totalPrice; //productPrice * quantity	
	private String narration; //transaction description

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="firmId", nullable=false, updatable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private FirmProfile firm;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public long getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(long billNumber) {
		this.billNumber = billNumber;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public FirmProfile getFirm() {
		return firm;
	}

	public void setFirm(FirmProfile firm) {
		this.firm = firm;
	}
}
