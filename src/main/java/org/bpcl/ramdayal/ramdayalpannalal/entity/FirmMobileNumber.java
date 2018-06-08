package org.bpcl.ramdayal.ramdayalpannalal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FirmMobileNumber extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5799201328358945186L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String mobileNumber;
	
	@ManyToOne
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public FirmProfile getFirm() {
		return firm;
	}

	public void setFirm(FirmProfile firm) {
		this.firm = firm;
	}
}
