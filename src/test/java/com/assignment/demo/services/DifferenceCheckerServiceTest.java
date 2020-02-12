package com.assignment.demo.services;

import com.assignment.demo.beans.Carrier;
import com.assignment.demo.beans.Difference;
import com.assignment.demo.repositories.CarrierFinderRepository;
import com.assignment.demo.repositories.DifferenceCheckerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DifferenceCheckerServiceTest {

    @Mock
    private CarrierFinderRepository carrierFinderRepository;
    @Mock
    private DifferenceCheckerRepository differenceCheckerRepository;
    @InjectMocks
    private DifferenceCheckerService service = new DifferenceCheckerService();

    private static List<Carrier> carriersMockOld = setCarriersMockOld();
    private List<Carrier> carriersMockNew = setCarriersMockNew();

    @Before
    public void setUpTest() {
        when(carrierFinderRepository.retrieveCarriersForced()).thenReturn(carriersMockNew);
        when(carrierFinderRepository.retrieveCarriers()).thenReturn(carriersMockOld);
        when(carrierFinderRepository.addCarriers(anyList())).thenReturn(carriersMockNew);
        when(carrierFinderRepository.addCarrier(any(Carrier.class))).thenReturn(1L);
        when(carrierFinderRepository.deleteAll(anyList())).thenReturn(true);
        when(differenceCheckerRepository.deleteAll()).thenReturn(true);
        when(differenceCheckerRepository.addDifference(anyList())).thenReturn(null);
    }

    @Test
    public void testEmptyDB() {
        when(carrierFinderRepository.isEmpty()).thenReturn(true);
        List<Difference> actualDiferences = new ArrayList<>();
        carriersMockNew.stream().forEach(carrier -> actualDiferences.add(new Difference(carrier.getFid(), true)));

        service.check();

        ArgumentCaptor<List<Carrier>> captorFinder = ArgumentCaptor.forClass(List.class);
        verify(carrierFinderRepository, times(1)).addCarriers(captorFinder.capture());

        assertNotNull(captorFinder.getValue());
        assertTrue(captorFinder.getValue().containsAll(carriersMockNew));
        assertEquals(captorFinder.getValue().size(), carriersMockNew.size());

        ArgumentCaptor<List<Difference>> captorDiff = ArgumentCaptor.forClass(List.class);
        verify(differenceCheckerRepository, times(1)).deleteAll();
        verify(differenceCheckerRepository, times(1)).addDifference(captorDiff.capture());

        assertNotNull(captorDiff.getValue());
        assertTrue(captorDiff.getValue().containsAll(actualDiferences));
        assertEquals(captorDiff.getValue().size(), actualDiferences.size());

    }

    public static List<Carrier> setCarriersMockOld() {
        List<Carrier> carriers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Carrier c = new Carrier();
            c.setId(i);
            c.setFid("old" + i);
            carriers.add(c);
        }
        return carriers;
    }

    public List<Carrier> setCarriersMockNew() {
        List<Carrier> carriers = new ArrayList<>();
        for (int i = 3; i < 8; i++) {
            Carrier c = new Carrier();
            c.setId(i);
            c.setFid("new" + i);
            carriers.add(c);
        }
        return carriers;
    }
}
