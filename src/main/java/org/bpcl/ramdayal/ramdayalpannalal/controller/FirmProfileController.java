package org.bpcl.ramdayal.ramdayalpannalal.controller;

import java.util.List;
import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmProfile;
import org.bpcl.ramdayal.ramdayalpannalal.service.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirmProfileController {

	@Autowired
	private FirmService firmService; 
	
	@RequestMapping("/Firms")
	public List<FirmProfile> getFirms(){
		return firmService.getAllFirms();
	}
	
	@RequestMapping("/Firms/ById/{firmId}")
	public Optional<FirmProfile> getFirm(@PathVariable String firmId) {
		return firmService.getFirmById(Long.parseLong(firmId));
	}
	
	@RequestMapping("/Firms/ByName/{firmName}")
	public Optional<FirmProfile> getFirmByName(@PathVariable String firmName) {
		return firmService.getFirmByDisplayname(firmName);
	}
	
	@RequestMapping(method=RequestMethod.POST,value = "/Firms")
	public void addFirm(@RequestBody FirmProfile firmProfile) {
		firmService.addFirm(firmProfile);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value = "/Firms/{firmId}")
	public void updateFirm(@RequestBody FirmProfile firmProfile,@PathVariable String firmId) {
		firmService.updateFirm(firmId,firmProfile);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/Firms/{firmId}")
	public void deleteFirm(@PathVariable String firmId) {
		firmService.deleteFirm(Long.parseLong(firmId));
	}
	
}
