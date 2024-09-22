package com.embarkx.firstjobapp.mapper;

import com.embarkx.firstjobapp.company.model.Company;
import com.embarkx.firstjobapp.dto.CompanyRequest;
import com.embarkx.firstjobapp.dto.CompanyResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyReuestMappper {

    public static List<CompanyResponse>companyListToCompanyResponseList(List<Company>companyList){
        return companyList.stream().map(company -> new CompanyResponse(
               company.getId(),
               company.getName(),
               company.getEmail(),
               company.getDescription(),
               company.getCreatedAt(),
               company.getUpdatedAt(),
                company.getJobs()
        )).collect(Collectors.toList());



    }



    public static Company CompanyResponseToCompany(CompanyResponse company){
        return new Company(
               company.id(),
               company.name(),
               company.email(),
               company.description(),
               company.createdAt(),
               company.updatedAt(),
                company.jobs()
        );
    }
    public static CompanyResponse CompanyToCompanyRequester(Company company){
        return new CompanyResponse(company.getId(),
               company.getName(),
               company.getEmail(),
               company.getDescription(),
               company.getCreatedAt(),
               company.getUpdatedAt(),
                company.getJobs()
        );
    }

    public static Company CompanyRequesterToCompany(CompanyRequest request, Company company){
       company.setName(request.name());
       company.setEmail(request.email());
       company.setDescription(request.description());
        return company;
    }
}
