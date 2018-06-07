package org.bpcl.ramdayal.ramdayalpannalal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"timestampAdded", "timestampUpdated"},
        allowGetters = true
)
public class AuditModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7242929639256099065L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TIMESTAMP_ADDED",nullable = false, updatable = false)
	@CreatedDate
	private Date timestampAdded;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TIMESTAMP_UPDATED",nullable = false)
	@LastModifiedDate
	private Date timestampUpdated;
	
	public Date getTimestampAdded() {
		return timestampAdded;
	}
	public void setTimestampAdded(Date timestampAdded) {
		this.timestampAdded = timestampAdded;
	}
	public Date getTimestampUpdated() {
		return timestampUpdated;
	}
	public void setTimestampUpdated(Date timestampUpdated) {
		this.timestampUpdated = timestampUpdated;
	}
	
	
}
