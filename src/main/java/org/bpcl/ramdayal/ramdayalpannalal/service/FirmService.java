package org.bpcl.ramdayal.ramdayalpannalal.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmMobileNumber;
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
		firmRepository.findAll().forEach(firm -> {
			setMobileNumber(firm);
			firms.add(firm);
		});
		
		return firms;
	}

	private void setMobileNumber(FirmProfile firm) {
		Set<FirmMobileNumber> mobileNumbers = new HashSet<>();
		mobileNumbers.addAll(firmMobileNumberRepository.findByFirmFirmId(firm.getFirmId()));
		firm.setMobileNumbers(mobileNumbers);
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
		saveMobileNumbers(firmProfile);
	}

	private void saveMobileNumbers(FirmProfile firmProfile) {
		long firmId = firmRepository.findOneByDisplayName(getDisplayName(firmProfile)).getFirmId();
		firmProfile.setFirmId(firmId);
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
	}

	public void deleteFirm(long firmId) {
		firmRepository.deleteById(firmId);
	}

	
	private void setDisplayName(FirmProfile firmProfile) {
		firmProfile.setDisplayName(getDisplayName(firmProfile));
	}

	private String getDisplayName(FirmProfile firmProfile) {
		return (firmProfile.getSupplyLocation()) != null ? firmProfile.getFirmName().concat(" - ").concat(firmProfile.getSupplyLocation()):firmProfile.getFirmName();
	}

	

}
