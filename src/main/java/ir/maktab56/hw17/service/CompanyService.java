package ir.maktab56.hw17.service;

import ir.maktab56.hw17.base.service.BaseEntityService;
import ir.maktab56.hw17.domain.Company;

import java.util.List;

public interface CompanyService  extends BaseEntityService<Company, Integer> {




    List<Company> addDefaultBranch();

    void showCompanyInfo();
}
