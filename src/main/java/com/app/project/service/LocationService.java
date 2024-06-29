package com.app.project.service;



import com.app.project.baseframework.exception.SystemException;
import com.app.project.entity.Country;

import java.util.List;

/**
 * Created by Manjunath Reddy
 */
public interface LocationService {

    List<Country> countryDetails() throws SystemException;

}