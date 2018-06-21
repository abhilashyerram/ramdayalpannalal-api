package org.bpcl.ramdayal.ramdayalpannalal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.entity.Firm;
import org.bpcl.ramdayal.ramdayalpannalal.repository.FirmEmailAddressRepository;
import org.bpcl.ramdayal.ramdayalpannalal.repository.FirmMobileNumberRepository;
import org.bpcl.ramdayal.ramdayalpannalal.repository.FirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirmService {

	@Autowired
	private FirmRepository firmRepository;
	@Autowired
	private FirmMobileNumberRepository firmMobileNumberRepository;
	@Autowired
	private FirmEmailAddressRepository firmEmailAddressRepository;
	
	public List<Firm> getAllFirms() {
		List<Firm> firms = new ArrayList<>();
		firmRepository.findAll().forEach(firms::add);
		return firms;
	}

	public Optional<Firm> getFirmById(long firmId) {
		return firmRepository.findById(firmId);
	}
	
	public Optional<Firm> getFirmByDisplayname(String firmName) {
		return firmRepository.findByDisplayNameIgnoreCaseContaining(firmName);
	}

	public void addFirm(Firm firmProfile) {
		setDisplayName(firmProfile);
		firmRepository.save(firmProfile);
		
		long firmId = firmRepository.findOneByDisplayName(getDisplayName(firmProfile)).getId();
		firmProfile.setId(firmId);
		
		saveMobileNumbers(firmProfile);
			
		saveEmailAddress(firmProfile);
	}

	private void saveEmailAddress(Firm firmProfile) {
		firmProfile.getEmailAddresses().forEach(emailAddress -> {
			emailAddress.setFirm(firmProfile);
			firmEmailAddressRepository.save(emailAddress);
		});
	}

	private void saveMobileNumbers(Firm firmProfile) {
		firmProfile.getMobileNumbers().forEach(mobileNumber -> {
			mobileNumber.setFirm(firmProfile);
			firmMobileNumberRepository.save(mobileNumber);
		});
	}

	public void updateFirm(String firmId, Firm firmProfile) {
		if(firmProfile.getId() == null)
		{
			throw new NullPointerException("Firm Id is required to update firm");
		}
		firmRepository.save(firmProfile);
		saveMobileNumbers(firmProfile);	
		saveEmailAddress(firmProfile);
	}

	public void deleteFirm(long firmId) {		
		//Deleting from child table
		deleteFirmMobileNumbers(firmId);
		deleteEmailAddresses(firmId);
		firmRepository.deleteById(firmId);
	}

	private void deleteEmailAddresses(long firmId) {
		firmEmailAddressRepository.findByFirmId(firmId).forEach(emailAddress -> {
			firmEmailAddressRepository.delete(emailAddress);
		});
	}

	private void deleteFirmMobileNumbers(long firmId) {
		firmMobileNumberRepository.findByFirmId(firmId).forEach(mobileNumber -> {
			firmMobileNumberRepository.delete(mobileNumber);
		});
	}

	private void setDisplayName(Firm firmProfile) {
		firmProfile.setDisplayName(getDisplayName(firmProfile));
	}

	private String getDisplayName(Firm firmProfile) {
		return (firmProfile.getSupplyLocation()) != null ? firmProfile.getFirmName().concat(" - ").concat(firmProfile.getSupplyLocation()):firmProfile.getFirmName();
	}
}
