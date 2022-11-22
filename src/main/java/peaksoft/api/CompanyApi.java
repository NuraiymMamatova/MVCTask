package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Company;
import peaksoft.service.CompanyService;

@Controller
@RequestMapping("/company_api")
public class CompanyApi {

    private final CompanyService companyService;

    @Autowired
    public CompanyApi(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/allOfCompanies")
    private String getAllCompanies(Model model) {
        model.addAttribute("allCompany", companyService.getAllCompanies());
        return "/company/allCompanies";
    }

    @GetMapping("/new")
    private String newCompany(Model model) {
        model.addAttribute("newCompany", new Company());
        return "/company/saveCompany";
    }

    @PostMapping("/save")
    private String saveCompany(@ModelAttribute("newCompany") Company company) {
        companyService.saveCompany(company);
        return "redirect:/company_api/allOfCompanies";
    }

    @GetMapping("/update")
    private String upCompany(@RequestParam("id") Long id, Model model) {
        model.addAttribute("updateCompany", companyService.getCompanyById(id));
        return "/company/updateCompany";
    }

    @PostMapping("/update")
    private String dateCompany(@ModelAttribute("updateCompany") Company company) {
        companyService.updateCompany(company);
        return "redirect:/company_api/allOfCompanies";
    }

    @RequestMapping("/delete")
    private String deleteCompany(@RequestParam("id") Long id) {
        companyService.deleteCompany(id);
        return "redirect:/company_api/allOfCompanies";
    }
}
