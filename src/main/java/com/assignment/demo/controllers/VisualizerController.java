/**
 *
 */
package com.assignment.demo.controllers;

import com.assignment.demo.beans.Assignment;
import com.assignment.demo.beans.Carrier;
import com.assignment.demo.beans.Difference;
import com.assignment.demo.constants.RestConstants;
import com.assignment.demo.exceptions.CarrierNotFoundException;
import com.assignment.demo.services.VisualizerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * @author dgaram
 *
 */
@RestController()
@RequestMapping(RestConstants.VISUALIZER_PREFIX)
public class VisualizerController {

    @Autowired
    private VisualizerService visualizerService;

    @ApiOperation(value = "Retrieve all carriers", notes = "Retrieve all carriers")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Carriers", response = Carrier.class, responseContainer = "List")})
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = RestConstants.REST_VISUALIZER_RETRIEVE_ALL_CARRIERS)
    public List<Carrier> retrieveCarriers() {
        return visualizerService.checkCarriersAvailable();
    }

    @ApiOperation(value = "Retrieve all differences", notes = "Retrieve all differences")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Differences", response = Difference.class, responseContainer = "List")})
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = RestConstants.REST_VISUALIZER_RETRIEVE_ALL_DIFFERENCES)
    public List<Difference> retrieveDifferences() {
        return visualizerService.checkDifferences();
    }


    @ApiOperation(value = "Check availability", notes = "Check whether a carrier is available or not")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Availability", response = Assignment.class),
            @ApiResponse(code = 404, message = "NOT FOUND")})
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = RestConstants.REST_VISUALIZER_RETRIEVE_AVAILABILITY)
    public boolean checkAvailability(@PathVariable("id") String id) {
        return visualizerService.checkIfAvailable(id);
    }

    @ApiOperation(value = "Add carrier", notes = "Add carrier")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Identifier", response = Long.class)})
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = RestConstants.REST_ASSIGNMENT_ADD)
    public Long addCarrier(@RequestBody String description) {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "Modify carrier", notes = "Modify carrier")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Void.class)})
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = RestConstants.REST_VISUALIZER_MODIFY)
    public void modifyCarrier(@PathVariable("id") long id) {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "Delete carrier", notes = "Delete carrier")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Boolean.class)})
    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, path = RestConstants.REST_VISUALIZER_DELETE)
    public boolean deleteCarrier(@PathVariable("id") long id) {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

}
