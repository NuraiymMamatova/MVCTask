package peaksoft.service;

import peaksoft.entity.Company;

import java.util.List;

public interface CompanyService {

    void saveCompany(Company company);

    void deleteCompany(Long id);

    void updateCompany(Company company);

    Company getCompanyById(Long id);

    List<Company> getAllCompanies();
}
