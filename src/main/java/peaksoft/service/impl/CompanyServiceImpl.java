package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Company;
import peaksoft.repository.CompanyRepository;
import peaksoft.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void saveCompany(Company company) {
        companyRepository.saveCompany(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteCompany(id);
    }

    @Override
    public void updateCompany(Company company) {
        companyRepository.updateCompany(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.getCompanyById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }
}
