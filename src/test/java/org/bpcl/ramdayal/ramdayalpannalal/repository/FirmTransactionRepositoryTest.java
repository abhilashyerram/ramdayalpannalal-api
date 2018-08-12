package org.bpcl.ramdayal.ramdayalpannalal.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bpcl.ramdayal.ramdayalpannalal.entity.Firm;
import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmTransaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FirmTransactionRepositoryTest {
	
	@Autowired
	private FirmTransactionRepository firmTransactionRepository;
	@Autowired 
	private FirmRepository firmRepository;
	@Autowired
	private TestEntityManager testEntityManager;
	
	private long firmId;
	private String firmName = "abc";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Date transactionDt,transactionDt1;
	
	@Before
	public void setUp() throws ParseException {
		
        String supplyLocation = "xyz";
        Firm firm = new Firm();
        firm.setFirmName(firmName);
        firm.setSupplyLocation(supplyLocation);
        firm.setDisplayName(firmName);
        
        
        String transactionDate = "2018-07-01";
        String transactionDate1 = "2018-07-02";
        transactionDt = sdf.parse(transactionDate);
        transactionDt1 = sdf.parse(transactionDate1);
        
        FirmTransaction firmTransaction = new FirmTransaction();
		firmTransaction.setFirm(firm);
		firmTransaction.setTransactionDate(transactionDt);
		firmTransaction.setTransactionType("credit");
		firmTransaction.setBillNumber(101);
		firmTransaction.setProductPrice((float) 50.00);
		firmTransaction.setQuantity(1000);
		firmTransaction.setAmount(50000);
		firmTransaction.setNarration("credit");
		
		FirmTransaction firmTransaction1 = new FirmTransaction();
		firmTransaction.setFirm(firm);
		firmTransaction.setTransactionDate(transactionDt1);
		firmTransaction.setTransactionType("debit");
		firmTransaction.setAmount(20000);
		firmTransaction.setNarration("debit");
		
		
		testEntityManager.persist(firm);
		testEntityManager.persist(firmTransaction);
		testEntityManager.persist(firmTransaction1);
		
		firmId = firmRepository.findByDisplayNameIgnoreCaseContaining(firmName).get().getId();
	}
	
	
	@Test
	public void testFindByFirmId() {
		assertEquals(firmTransactionRepository.findByFirmId(firmId).iterator().next().getBillNumber(), 101);
		assertEquals(firmTransactionRepository.findByFirmId(firmId).iterator().next().getTransactionType(), "debit");
	}

	@Test
	public void testFindByTransactionDate() {
		assertTrue(firmTransactionRepository.findByTransactionDate(firmId, transactionDt, transactionDt1).iterator().hasNext());
	}
}
