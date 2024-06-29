package com.app.project.repository;



import com.app.project.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Manjunath Reddy
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

}