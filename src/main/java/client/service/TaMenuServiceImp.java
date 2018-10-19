package client.service;


import client.dao.TaMenuDao;
import client.pojo.TaMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaMenuServiceImp implements TaMenuService {
    @Autowired(required = false)
    TaMenuDao taMenuDao;
    @Override
    public List<TaMenu> findUserMenus(String userName) {
        return this.taMenuDao.findUserMenus(userName);
    }

    @Override
    public String findUserPermissions(String userName) {
        List<TaMenu> list = taMenuDao.findUserPermissions(userName);
        return list.stream().map(TaMenu::getPerms).collect(Collectors.joining(","));
    }
    @Override
    public Set<String> listUserPermissions(String userName) {
        List<TaMenu> list = taMenuDao.findUserPermissions(userName);
        return list.stream().map(TaMenu::getPerms).collect(Collectors.toSet());
    }
}
