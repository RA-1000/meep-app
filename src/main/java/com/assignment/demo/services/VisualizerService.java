/**
 *
 */
package com.assignment.demo.services;

import com.assignment.demo.aspects.LoggingAspect;
import com.assignment.demo.beans.Carrier;
import com.assignment.demo.beans.Difference;
import com.assignment.demo.exceptions.CarrierNotFoundException;
import com.assignment.demo.repositories.CarrierFinderRepository;
import com.assignment.demo.repositories.DifferenceCheckerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dgaram
 *
 */
@Service
public class VisualizerService {
    @Autowired
    private CarrierFinderRepository carrierFinderRepository;
    @Autowired
    private DifferenceCheckerRepository differenceCheckerRepository;
    @Autowired
    private DifferenceCheckerService differenceCheckerService;

    private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    public List<Carrier> checkCarriersAvailable() {
        List<Carrier> aux=carrierFinderRepository.retrieveCarriers();
        return aux!=null&&!aux.isEmpty()?aux:carrierFinderRepository.retrieveCarriersForced();
    }

    public List<Difference> checkDifferences() {
        List<Difference> aux=differenceCheckerRepository.retrieveAllDifferences();
        return aux!=null&&!aux.isEmpty()?aux:differenceCheckerService.check();
    }

    public boolean checkIfAvailable(String id) {
        Difference aux = differenceCheckerRepository.retrieveDifference(id);
        if (aux != null) {
            log.debug("found");
            return aux.isExist();
        } else throw new CarrierNotFoundException();
    }
}
