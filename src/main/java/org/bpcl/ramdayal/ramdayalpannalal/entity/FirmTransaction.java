package org.bpcl.ramdayal.ramdayalpannalal.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FirmTransaction extends AuditModel{

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

	@ManyToOne
	@JoinColumn(name="firmId", nullable=false, updatable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private FirmProfile firm;


}
