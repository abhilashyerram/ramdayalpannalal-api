package org.bpcl.ramdayal.ramdayalpannalal.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.bpcl.ramdayal.ramdayalpannalal.entity.Firm;
import org.bpcl.ramdayal.ramdayalpannalal.repository.FirmRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FirmServiceTest {
	@InjectMocks
	private FirmService firmService;
	private Iterable<Firm> firms = new ArrayList<Firm>();
	private long firmId = 1;
	
	@Mock
	FirmRepository firmRepository;
	@Mock 
	Firm firm;
	
	private Optional<Firm> optionalFirm;
	
	@Before
	public void setUp() {
		firmService = new FirmService();
		((ArrayList<Firm>) firms).add(firm);
	}
	
	@Before
	public void setUpMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(firmRepository.findAll()).thenReturn(firms);
		//when(firmRepository.findById(firmId)).thenReturn(optionalFirm);
	}

	@Test
	public void getAllFirmsTest() throws Exception {
		assertNotNull(firmService.getAllFirms());
	}
	
	
	
}
