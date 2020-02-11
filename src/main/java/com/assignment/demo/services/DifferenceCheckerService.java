package com.assignment.demo.services;

import com.assignment.demo.aspects.LoggingAspect;
import com.assignment.demo.beans.Carrier;
import com.assignment.demo.beans.Difference;
import com.assignment.demo.repositories.CarrierFinderRepository;
import com.assignment.demo.repositories.DifferenceCheckerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class DifferenceCheckerService {
    @Autowired
    private CarrierFinderRepository carrierFinderRepository;
    @Autowired
    private DifferenceCheckerRepository differenceCheckerRepository;

    private static ExecutorService promiseMaker = Executors.newCachedThreadPool();

    private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    public List<Difference> check() {
        /*
        llamar finder force;
        ver si existen:

        add to db, pisar finder
         */
        List<Difference> differences = new ArrayList<>();
        try {

            Future<List<Carrier>> nCarriersTask = promiseMaker.submit(
                    () -> carrierFinderRepository.retrieveCarriersForced());
            Future<List<Carrier>> oCarriersTask = promiseMaker.submit(
                    () -> !carrierFinderRepository.isEmpty() ? carrierFinderRepository.retrieveCarriers() : null);

            List<Carrier> oCarriers = oCarriersTask.get();
            List<Carrier> nCarriers = nCarriersTask.get();

            if (oCarriers == null || oCarriers.isEmpty()) {
                //mode first time.
                nCarriers.stream().forEach(carrier -> {
                    differences.add(new Difference(carrier.getFid(), true));
                });
                promiseMaker.execute(() ->
                        carrierFinderRepository.addCarriers(nCarriers));
            } else {
                //nexts
                nCarriers.stream().forEach(carrier -> {
                    if (!oCarriers.contains(carrier)) {
                        differences.add(new Difference(carrier.getFid(), true));
                        promiseMaker.execute(() -> carrierFinderRepository.addCarrier(carrier));
                    }
                });

                if (oCarriers.removeAll(nCarriers)) {
                    oCarriers.stream().forEach(carrier -> {
                        differences.add(new Difference(carrier.getFid(), false));
                    });
                    promiseMaker.execute(() -> carrierFinderRepository.deleteAll(oCarriers));
                }
            }
            promiseMaker.submit(() -> {
                differenceCheckerRepository.deleteAll();
                differenceCheckerRepository.addDifference(differences);
            });
        } catch (InterruptedException e) {
            log.error("Process interrupted", e);
        } catch (ExecutionException e) {
            log.error("Process interrupted", e);
        }
        while (((ThreadPoolExecutor) promiseMaker).getActiveCount() > 0) ;
        return differences;
    }

}
