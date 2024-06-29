package com.app.project.controller;


import com.app.project.baseframework.controller.BaseController;
import com.app.project.baseframework.exception.BaseException;
import com.app.project.entity.Country;
import com.app.project.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Manjunath Reddy
 */
@RestController
@RequestMapping(ApiRoute.BASE_MAPPING_API_V1)
@CrossOrigin(origins = "*")
public class LocationController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @GetMapping(value = ApiRoute.LOCATION_COUNTRY)
    public ResponseEntity<List<Country>> countryDetails() throws BaseException {
        logger.info("countryDetails master data");
        return new ResponseEntity<>(locationService.countryDetails(), HttpStatus.OK);
    }

}
