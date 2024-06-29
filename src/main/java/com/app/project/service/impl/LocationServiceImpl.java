package com.app.project.service.impl;



import com.app.project.baseframework.exception.SystemException;
import com.app.project.entity.City;
import com.app.project.entity.Country;
import com.app.project.entity.State;
import com.app.project.repository.CityRepository;
import com.app.project.repository.CountryRepository;
import com.app.project.repository.StateRepository;
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
    @Autowired
    StateRepository stateRepository;
    @Autowired
    CityRepository cityRepository;


    @Override
    public List<Country> countryDetails() throws SystemException {
        List<Country> countryList = countryRepository.findAll();
        logger.info("countryDetails master data of size : {}", countryList.size());
        return countryList;
    }
    @Override
    public List<State> stateDetailsByCountryId(Long countryId) throws SystemException {
        List<State> stateList = stateRepository.stateDetailsByCountry(countryId);
        logger.info("stateDetailsByCountryId master data data of size : {}", stateList.size());
        return stateList;
    }

    @Override
    public List<City> cityDetailsByStateId(Long stateId) throws SystemException {
        List<City> cityList = cityRepository.cityDetailsForStateNameByStateId(stateId);
        logger.info("cityDetailsByStateId master data data of size : {}", cityList.size());
        return cityList;
    }


}
