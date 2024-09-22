package com.embarkx.firstjobapp.company.controller;

import com.embarkx.firstjobapp.company.service.CompanyService;
import com.embarkx.firstjobapp.dto.CompanyRequest;
import com.embarkx.firstjobapp.dto.CompanyResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
@Validated
public class CompanyController {
    private final CompanyService service;


    @PostMapping("/save")
    public ResponseEntity<CompanyResponse> createCompany( @RequestBody @Valid CompanyRequest job) {
        return new ResponseEntity<>(service.createCompany( job), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompanyResponse>> getAllCompany( ) {
        return ResponseEntity.of(Optional.ofNullable(service.findAllCompany( )));
    }



    @PutMapping("/{companyID}")
    public ResponseEntity<CompanyResponse> updateCompany( @PathVariable
                                                 @NotNull(message = "Company ID cannot be empty")
                                                     String companyID,
                                                     @RequestBody  @Valid CompanyRequest request) {
        return ResponseEntity.
                status(HttpStatus.OK).
                body(service.updateCompany( companyID,request));
    }
    @GetMapping("/{companyID}")
    public ResponseEntity<CompanyResponse> findCompanybByID(@PathVariable
                                                 @NotNull(message = "Company ID cannot be empty")

                                                     String companyID) {
        return ResponseEntity.ok(service.findCompanyById(companyID));
    }
    @DeleteMapping("/{companyID}")
    public ResponseEntity<?> deleteCompany( @PathVariable
                                                 @NotNull(message = "Company ID cannot be empty")

                                           String companyID) {
        var deleted=service.deleteCompanyById(companyID);
        if (deleted){
            return new ResponseEntity<>("Company deleted",HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
