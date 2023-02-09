package solvd.service.impl;

import solvd.dao.TransactionDao;
import solvd.model.Transaction;
import solvd.service.TransactionService;

import java.util.Collection;

public class TransactionServiceImpl implements TransactionService {
    private TransactionDao transactionDao;

    public TransactionServiceImpl() {
        transactionDao = new TransactionDao();
    }

    @Override
    public boolean save(Transaction entity) {
        return transactionDao.insert(entity);
    }

    @Override
    public Collection<Transaction> getAll() {
        return transactionDao.getAll();
    }

    @Override
    public Transaction getById(Integer id) {
        return transactionDao.getEntityById(id);
    }

    @Override
    public void update(Transaction newEntity, Integer id) {
        transactionDao.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return transactionDao.delete(id);
    }
}
