package solvd.service.impl;

import solvd.dao.impl.BranchDaoImpl;
import solvd.model.Branch;
import solvd.service.BranchService;

import java.util.Collection;

public class BranchServiceImpl implements BranchService {
    private BranchDaoImpl branchDaoImpl;

    public BranchServiceImpl() {
        branchDaoImpl = new BranchDaoImpl();
    }

    @Override
    public boolean save(Branch entity) {
        return branchDaoImpl.insert(entity);
    }

    @Override
    public Collection<Branch> getAll() {
        return branchDaoImpl.getAll();
    }

    @Override
    public Branch getById(Integer id) {
        return branchDaoImpl.getEntityById(id);
    }

    @Override
    public void update(Branch newEntity, Integer id) {
        branchDaoImpl.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return branchDaoImpl.delete(id);
    }
}
