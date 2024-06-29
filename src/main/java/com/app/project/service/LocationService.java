package com.app.project.service;



import com.app.project.baseframework.exception.SystemException;
import com.app.project.entity.City;
import com.app.project.entity.Country;
import com.app.project.entity.State;

import java.util.List;

/**
 * Created by Manjunath Reddy
 */
public interface LocationService {

    List<Country> countryDetails() throws SystemException;
    List<State> stateDetailsByCountryId(Long countryId) throws SystemException;

    List<City> cityDetailsByStateId(Long stateId) throws SystemException;

}