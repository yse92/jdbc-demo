package solvd.service.impl;

import solvd.dao.LoanDao;
import solvd.model.Loan;
import solvd.service.LoanService;

import java.util.Collection;

public class LoanServiceImpl implements LoanService {
    private LoanDao loanDao;

    public LoanServiceImpl() {
        loanDao = new LoanDao();
    }

    @Override
    public boolean save(Loan entity) {
        return loanDao.insert(entity);
    }

    @Override
    public Collection<Loan> getAll() {
        return loanDao.getAll();
    }

    @Override
    public Loan getById(Integer id) {
        return loanDao.getEntityById(id);
    }

    @Override
    public void update(Loan newEntity, Integer id) {
        loanDao.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return loanDao.delete(id);
    }
}
