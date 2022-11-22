package peaksoft.repository;

import peaksoft.entity.Company;

import java.util.List;

public interface CompanyRepository {

    void saveCompany(Company company);

    void deleteCompany(Long id);

    void updateCompany(Company company);

    Company getCompanyById(Long id);

    List<Company> getAllCompanies();
}