package org.bpcl.ramdayal.ramdayalpannalal.controller;

import org.bpcl.ramdayal.ramdayalpannalal.entity.Firm;
import org.bpcl.ramdayal.ramdayalpannalal.service.FirmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FirmProfileController {

	private static final Logger log = LoggerFactory.getLogger(FirmProfileController.class);

	@Autowired
	private FirmService firmService; 
	
	@RequestMapping("/firms")
	public List<Firm> getAllFirms(){
		log.info("Entered getAllFirms()");
		return firmService.getAllFirms();
	}
	
	@RequestMapping("/firms/byid/{firmId}")
	public Optional<Firm> getFirm(@PathVariable long firmId) {
		return firmService.getFirmById(firmId);
	}
	
	@RequestMapping("/firms/byname/{firmName}")
	public Optional<Firm> getFirmByName(@PathVariable String firmName) {
		return firmService.getFirmByDisplayname(firmName);
	}
	
	@RequestMapping(method=RequestMethod.POST,value = "/firms")
	public void addFirm(@RequestBody Firm firmProfile) {
		firmService.addFirm(firmProfile);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value = "/firms/{firmId}")
	public void updateFirm(@RequestBody Firm firmProfile,@PathVariable String firmId) {
		firmService.updateFirm(firmId,firmProfile);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/firms/{firmId}")
	public void deleteFirm(@PathVariable String firmId) {
		firmService.deleteFirm(Long.parseLong(firmId));
	}
	
}
