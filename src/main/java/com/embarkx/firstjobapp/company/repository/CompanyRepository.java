package com.embarkx.firstjobapp.company.repository;

import com.embarkx.firstjobapp.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,String> {


}
