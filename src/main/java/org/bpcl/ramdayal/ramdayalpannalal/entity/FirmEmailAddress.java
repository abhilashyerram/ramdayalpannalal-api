package org.bpcl.ramdayal.ramdayalpannalal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
public class FirmEmailAddress extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2909943694096819299L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String emailAddress;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private Firm firm;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Firm getFirm() {
		return firm;
	}

	public void setFirm(Firm firm) {
		this.firm = firm;
	}
}
