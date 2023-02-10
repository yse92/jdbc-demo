package solvd.service.impl;

import solvd.dao.impl.LoginDaoImpl;
import solvd.model.Login;
import solvd.service.LoginService;

import java.util.Collection;

public class LoginServiceImpl implements LoginService {
    private LoginDaoImpl loginDaoImpl;

    public LoginServiceImpl() {
        loginDaoImpl = new LoginDaoImpl();
    }

    @Override
    public boolean save(Login entity) {
        return loginDaoImpl.insert(entity);
    }

    @Override
    public Collection<Login> getAll() {
        return loginDaoImpl.getAll();
    }

    @Override
    public Login getById(Integer id) {
        return loginDaoImpl.getEntityById(id);
    }

    @Override
    public void update(Login newEntity, Integer id) {
        loginDaoImpl.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return loginDaoImpl.delete(id);
    }

}
