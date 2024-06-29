package com.app.project.service.impl;



import com.app.project.baseframework.exception.SystemException;
import com.app.project.entity.Country;
import com.app.project.repository.CountryRepository;
import com.app.project.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Manjunath Reddy
 */
@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger logger = LoggerFactory.getLogger(LocationService.class);

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Country> countryDetails() throws SystemException {
        List<Country> countryList = countryRepository.findAll();
        logger.info("countryDetails master data of size : {}", countryList.size());
        return countryList;
    }



}
