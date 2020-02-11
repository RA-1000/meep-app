/**
 *
 */
package com.assignment.demo.repositories;

import java.util.List;

import com.assignment.demo.beans.Carrier;
import com.assignment.demo.repositories.dao.CarrierFinderHelper;
import com.assignment.demo.tools.RestClientTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * @author dgaram
 *
 */
@Repository
public class CarrierFinderRepository {
    /*
     *  constants.
     * this is not allocated on constants class because only this repo will have any use with these
     */
    private static final String URI_METHOD_RESOURCES = "resources";
    private static final String URI_PARAMETER_LOWERLEFT_LATLON = "lowerLeftLatLon";
    private static final String URI_PARAMETER_UPPERRIGHT_LATLON = "upperRightLatLon";
    private static final String URI_PARAMETER_COMPZONES_IDS = "companyZoneIds";

    //repo sources:daos, tools, etc
    private CarrierFinderHelper helper;
    private RestClientTool<Carrier> carrierRestClientTool;

    //functional constants
    private boolean empty = true;

    @Autowired
    public void setHelper(CarrierFinderHelper helper) {
        this.helper = helper;
    }

    @Autowired
    public void setCarrierRestClientTool(RestClientTool carrierRestClientTool) {
        this.carrierRestClientTool = carrierRestClientTool;
    }

    public List<Carrier> retrieveCarriers() {
        return helper.findAll();
    }

    public List<Carrier> retrieveCarriersForced() {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add(URI_PARAMETER_LOWERLEFT_LATLON, "38.711046,-9.160096");
        parameters.add(URI_PARAMETER_UPPERRIGHT_LATLON, "38.739429,-9.137115");
        parameters.add(URI_PARAMETER_COMPZONES_IDS, "545,467,473");
        return carrierRestClientTool.get(URI_METHOD_RESOURCES, parameters, Carrier.class);
    }

    public long addCarrier(Carrier c) {
        Carrier aux = helper.save(c);
        return aux != null ? aux.getId() : -1L;
    }

    /**
     * Simple add-a-lot carriers. This is the same as {@link CarrierFinderRepository#addCarrier(Carrier)} but to add multiple elements.
     * @param cs
     * @return
     */
    public List<Carrier> addCarriers(List<Carrier> cs) {
        return helper.saveAll(cs);
    }

    public boolean deleteAll(List<Carrier> obsoleteCarriers) {
        helper.deleteAll(obsoleteCarriers);
        return true;
    }

    public boolean isEmpty() {
        if (empty) {
            empty = helper.count() > 0;
        }
        return empty;
    }
}
