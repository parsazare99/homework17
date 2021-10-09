package ir.maktab56.hw17.service.imp;

import ir.maktab56.hw17.base.service.impl.BaseEntityServiceImpl;
import ir.maktab56.hw17.domain.Company;
import ir.maktab56.hw17.repository.CompanyRepository;
import ir.maktab56.hw17.service.CompanyService;

import java.util.ArrayList;
import java.util.List;

public class CompanyServiceImpl extends BaseEntityServiceImpl<Company, Integer, CompanyRepository>
        implements CompanyService {
    public CompanyServiceImpl(CompanyRepository repository) {
        super(repository);
    }

    @Override
    public List<Company> addDefaultBranch() {
        List<Company> bankList = new ArrayList<>();
        if (findAll().size() == 0) {
            Company company1 = new Company("Ata", "09119578733", "Tehran ....");
            Company company2 = new Company("Varesh", "09217017363", "Tehran ....");
            Company company3 = new Company("Aseman", "09119598416", "Tehran ....");
            Company company4 = new Company("Taban", "09115214968", "Tehran ....");
            Company company5 = new Company("Saha", "09103911658", "Tehran ....");
            Company company6 = new Company("Caspian", "09127756521", "Tehran ....");
            save(company1);
            save(company2);
            save(company3);
            save(company4);
            save(company5);
            save(company6);
            bankList.add(company1);
            bankList.add(company2);
            bankList.add(company3);
            bankList.add(company4);
            bankList.add(company5);
            bankList.add(company6);
        }
        return bankList;
    }

    @Override
    public void showCompanyInfo() {

        List<Company> all = findAll();

        for (Company company : all) {
            System.out.println(company.toString());
        }


    }
}
