package solvd.service.impl;

import solvd.dao.impl.TransactionDaoImpl;
import solvd.model.Transaction;
import solvd.service.TransactionService;

import java.util.Collection;

public class TransactionServiceImpl implements TransactionService {
    private TransactionDaoImpl transactionDaoImpl;

    public TransactionServiceImpl() {
        transactionDaoImpl = new TransactionDaoImpl();
    }

    @Override
    public boolean save(Transaction entity) {
        return transactionDaoImpl.insert(entity);
    }

    @Override
    public Collection<Transaction> getAll() {
        return transactionDaoImpl.getAll();
    }

    @Override
    public Transaction getById(Integer id) {
        return transactionDaoImpl.getEntityById(id);
    }

    @Override
    public void update(Transaction newEntity, Integer id) {
        transactionDaoImpl.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return transactionDaoImpl.delete(id);
    }
}
