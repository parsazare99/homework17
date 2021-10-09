package ir.maktab56.hw17.repository.imp;

import ir.maktab56.hw17.base.repository.imp.BaseEntityRepositoryImpl;
import ir.maktab56.hw17.domain.Company;
import ir.maktab56.hw17.repository.CompanyRepository;

import javax.persistence.EntityManager;

public class CompanyRepositoryImpl  extends BaseEntityRepositoryImpl<Company, Integer> implements CompanyRepository {
    public CompanyRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Company> getEntityClass() {
        return Company.class;
    }
}
