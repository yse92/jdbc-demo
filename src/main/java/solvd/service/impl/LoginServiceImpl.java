package solvd.service.impl;

import solvd.dao.LoginDao;
import solvd.model.Login;
import solvd.service.LoginService;

import java.util.Collection;

public class LoginServiceImpl implements LoginService {
    private LoginDao loginDao;

    public LoginServiceImpl() {
        loginDao  = new LoginDao();
    }

    @Override
    public boolean save(Login entity) {
        return loginDao.insert(entity);
    }

    @Override
    public Collection<Login> getAll() {
        return loginDao.getAll();
    }

    @Override
    public Login getById(Integer id) {
        return loginDao.getEntityById(id);
    }

    @Override
    public void update(Login newEntity, Integer id) {
        loginDao.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return loginDao.delete(id);
    }

}
