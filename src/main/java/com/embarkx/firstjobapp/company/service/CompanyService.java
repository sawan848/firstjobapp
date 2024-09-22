package com.embarkx.firstjobapp.company.service;

import com.embarkx.firstjobapp.dto.CompanyRequest ;
import com.embarkx.firstjobapp.dto.CompanyResponse ;

import java.util.List;

public interface CompanyService {
     List<CompanyResponse >findAllCompany();
     CompanyResponse  createCompany(CompanyRequest  job);
     CompanyResponse  updateCompany(String companyID,CompanyRequest  job);
     CompanyResponse  findCompanyById(String companyID);
     boolean  deleteCompanyById(String companyID);

}
