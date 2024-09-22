package com.embarkx.firstjobapp.company.service.impl;

import com.embarkx.firstjobapp.company.model.Company;
import com.embarkx.firstjobapp.company.repository.CompanyRepository;
import com.embarkx.firstjobapp.company.service.CompanyService;
import com.embarkx.firstjobapp.dto.CompanyRequest;
import com.embarkx.firstjobapp.dto.CompanyResponse;
import com.embarkx.firstjobapp.exception.CompanyNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static com.embarkx.firstjobapp.mapper.CompanyReuestMappper.*;
import static com.embarkx.firstjobapp.mapper.CompanyReuestMappper.CompanyToCompanyRequester;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;
    @Override
    public List<CompanyResponse> findAllCompany() {
        return companyListToCompanyResponseList(repository.findAll());

    }

    @Override
    public CompanyResponse  createCompany(CompanyRequest job) {
      try{
          var format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
          String created=format.format(ZonedDateTime.now());
          var request=CompanyRequesterToCompany(job,new Company());
          request.setId(UUID.randomUUID().toString());
          request.setCreatedAt(created);
          request.setUpdatedAt(created);
          log.info("created {}",request);
//          System.out.println("save = " + save);
          var save=repository.save(request);
         return CompanyToCompanyRequester(save);


      }catch (CompanyNotFoundException e){
          throw new CompanyNotFoundException("Company not created ");
      }

    }

    @Override
    public CompanyResponse  updateCompany(String companyID, CompanyRequest  job) {
        try {
            var format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
            String updated=format.format(ZonedDateTime.now());

            var response=findCompanyById(companyID);
            if (response!=null){
              CompanyResponse  updateResponse =  response.update(job.name(),
                        job.email(),
                        job.description(),
                        updated,
                      new ArrayList<>());
              var OriginalResponse=repository.save(
                      CompanyResponseToCompany(updateResponse));
                log.info("created {}",response);

                return CompanyToCompanyRequester(OriginalResponse);
//                repository.save(response);

            }
            throw new CompanyNotFoundException("Company is not updated with id "+companyID);
        }catch (CompanyNotFoundException e){
            throw new CompanyNotFoundException("Company is not updated with id "+companyID);

        }


    }

    @Override
    public CompanyResponse  findCompanyById(String companyID) {
        Company company = repository.
                findById(companyID).
                orElseThrow(() -> new CompanyNotFoundException("Company is not found with id" + companyID));

        return  CompanyToCompanyRequester(company);
    }

    @Override
    public boolean deleteCompanyById(String companyID) {
        Iterator<CompanyResponse > iterator=findAllCompany().iterator();
        while (iterator.hasNext()){
            CompanyResponse  response=iterator.next();
            if (response.id().equals(companyID)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }


}
