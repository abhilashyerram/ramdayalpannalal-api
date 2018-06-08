package org.bpcl.ramdayal.ramdayalpannalal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmProfile;
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
	
	public List<FirmProfile> getAllFirms() {
		List<FirmProfile> firms = new ArrayList<>();
		firmRepository.findAll().forEach(firms::add);
		return firms;
	}

	public Optional<FirmProfile> getFirmById(long firmId) {
		return firmRepository.findById(firmId);
	}
	
	public Optional<FirmProfile> getFirmByDisplayname(String firmName) {
		return firmRepository.findByDisplayNameIgnoreCaseContaining(firmName);
	}

	public void addFirm(FirmProfile firmProfile) {
		setDisplayName(firmProfile);
		firmRepository.save(firmProfile);
		
		long firmId = firmRepository.findOneByDisplayName(getDisplayName(firmProfile)).getFirmId();
		firmProfile.setFirmId(firmId);
		
		saveMobileNumbers(firmProfile);
	}

	private void saveMobileNumbers(FirmProfile firmProfile) {
		firmProfile.getMobileNumbers().forEach(mobileNumber -> {
			mobileNumber.setFirm(firmProfile);
			firmMobileNumberRepository.save(mobileNumber);
		});
	}

	public void updateFirm(String firmId, FirmProfile firmProfile) {
		if(firmProfile.getFirmId() == null)
		{
			throw new NullPointerException("Firm Id is required to update firm");
		}
		firmRepository.save(firmProfile);
		saveMobileNumbers(firmProfile);		
	}

	public void deleteFirm(long firmId) {		
		//Deleting from child table
		deleteFirmMobileNumbers(firmId);
		firmRepository.deleteById(firmId);
	}

	private void deleteFirmMobileNumbers(long firmId) {
		firmMobileNumberRepository.findByFirmFirmId(firmId).forEach(mobileNumber -> {
			firmMobileNumberRepository.delete(mobileNumber);
		});
	}

	private void setDisplayName(FirmProfile firmProfile) {
		firmProfile.setDisplayName(getDisplayName(firmProfile));
	}

	private String getDisplayName(FirmProfile firmProfile) {
		return (firmProfile.getSupplyLocation()) != null ? firmProfile.getFirmName().concat(" - ").concat(firmProfile.getSupplyLocation()):firmProfile.getFirmName();
	}
}
