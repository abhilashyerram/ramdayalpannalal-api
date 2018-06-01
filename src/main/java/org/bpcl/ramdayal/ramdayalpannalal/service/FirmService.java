package org.bpcl.ramdayal.ramdayalpannalal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmProfile;
import org.bpcl.ramdayal.ramdayalpannalal.repository.FirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirmService {

	@Autowired
	private FirmRepository firmRepository;
	
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
