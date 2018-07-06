package org.bpcl.ramdayal.ramdayalpannalal.repository;

import org.bpcl.ramdayal.ramdayalpannalal.entity.Firm;
import org.bpcl.ramdayal.ramdayalpannalal.entity.FirmMobileNumber;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FirmMobileNumberRepositoryTest {

    @Autowired
    private FirmMobileNumberRepository firmMobileNumberRepository;
    @Autowired private FirmRepository firmRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    private final long mobileNumber = 23143452;
    private final String displayName = "abc - xyz";

    @Before
    public void setUp() {
        String firmName = "abc";
        String supplyLocation = "xyz";
        Firm firmProfile = new Firm(firmName, supplyLocation, displayName);
        FirmMobileNumber firmMobileNumber = new FirmMobileNumber(mobileNumber, firmProfile);

        testEntityManager.persist(firmProfile);
        testEntityManager.persist(firmMobileNumber);
        testEntityManager.flush();
    }

    @Test
    public void findByFirmId() {

        if (firmRepository.findByDisplayNameIgnoreCaseContaining(displayName).isPresent()) {
            long firmId = firmRepository.findByDisplayNameIgnoreCaseContaining(displayName).get().getId();

            Iterable<FirmMobileNumber> firmMobileNumbers = firmMobileNumberRepository.findByFirmId(firmId);
            assertEquals(firmMobileNumbers.iterator().next().getMobileNumber(), mobileNumber);
        }
    }
}