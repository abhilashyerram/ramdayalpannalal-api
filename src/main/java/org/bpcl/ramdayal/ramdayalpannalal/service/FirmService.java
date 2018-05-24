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
//		firmRepository.findAll().forEach(firm -> {
//			firms.add(firm);
//		});
		firmRepository.findAll().forEach(firms::add);
		return firms;
	}

	public Optional<FirmProfile> getFirm(int firmId) {
		return firmRepository.findById(firmId);
	}

	public void addFirm(FirmProfile firmProfile) {
		firmRepository.save(firmProfile);
	}

	public void updateFirm(String firmId, FirmProfile firmProfile) {
		firmRepository.save(firmProfile);
	}

	public void deleteFirm(int firmId) {
		firmRepository.deleteById(firmId);
	}

	

}
