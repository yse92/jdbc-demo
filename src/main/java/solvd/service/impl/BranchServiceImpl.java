package solvd.service.impl;

import solvd.dao.BranchDao;
import solvd.model.Branch;
import solvd.service.BranchService;

import java.util.Collection;

public class BranchServiceImpl implements BranchService {
    private BranchDao branchDao;

    public BranchServiceImpl() {
        branchDao  = new BranchDao();
    }

    @Override
    public boolean save(Branch entity) {
        return branchDao.insert(entity);
    }

    @Override
    public Collection<Branch> getAll() {
        return branchDao.getAll();
    }

    @Override
    public Branch getById(Integer id) {
        return branchDao.getEntityById(id);
    }

    @Override
    public void update(Branch newEntity, Integer id) {
        branchDao.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return branchDao.delete(id);
    }
}
