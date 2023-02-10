package solvd.service.impl;

import solvd.dao.impl.LoanDaoImpl;
import solvd.model.Loan;
import solvd.service.LoanService;

import java.util.Collection;

public class LoanServiceImpl implements LoanService {
    private LoanDaoImpl loanDaoImpl;

    public LoanServiceImpl() {
        loanDaoImpl = new LoanDaoImpl();
    }

    @Override
    public boolean save(Loan entity) {
        return loanDaoImpl.insert(entity);
    }

    @Override
    public Collection<Loan> getAll() {
        return loanDaoImpl.getAll();
    }

    @Override
    public Loan getById(Integer id) {
        return loanDaoImpl.getEntityById(id);
    }

    @Override
    public void update(Loan newEntity, Integer id) {
        loanDaoImpl.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return loanDaoImpl.delete(id);
    }
}
