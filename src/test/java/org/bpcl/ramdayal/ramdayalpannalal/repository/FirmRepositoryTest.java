package org.bpcl.ramdayal.ramdayalpannalal.repository;

import static org.junit.Assert.assertEquals;

import org.bpcl.ramdayal.ramdayalpannalal.entity.Firm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FirmRepositoryTest {

    @Autowired private FirmRepository firmRepository;
    @Autowired private TestEntityManager testEntityManager;

    private final String firmName = "abc";
    private final String supplyLocation = "xyz";
    private final String displayName = "abc - xyz";

    @Before
    public void setUp() {
        Firm firmProfile = new Firm();
        firmProfile.setFirmName(firmName);
        firmProfile.setSupplyLocation(supplyLocation);
        firmProfile.setDisplayName(displayName);
        testEntityManager.persist(firmProfile);
        //testEntityManager.flush();
    }

    @Test
    public void findByDisplayNameIgnoreCaseContaining() {

        if(firmRepository.findByDisplayNameIgnoreCaseContaining(supplyLocation).isPresent())
        assertEquals(firmRepository.findByDisplayNameIgnoreCaseContaining(supplyLocation).get().getFirmName(),firmName);

    }

    @Test
    public void findOneByDisplayName() {
        assertEquals(firmRepository.findOneByDisplayName(displayName).getSupplyLocation(),supplyLocation );
    }
}