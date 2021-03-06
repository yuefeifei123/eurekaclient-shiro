package client.service;


import client.dao.TaRoleDao;
import client.dao.TaRoleMenuDao;
import client.pojo.TaRole;
import client.pojo.TaRoleMenu;
import client.pojo.TaUser;
import client.pojo.TaUserRole;
import client.pojo.example.TaRoleExample;
import client.pojo.example.TaRoleMenuExample;
import client.pojo.example.TaUserExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TaRoleServiceImp implements TaRoleService {
    @Autowired(required = false)
    TaRoleDao taRoleDao;
    @Autowired(required = false)
    TaRoleMenuDao taRoleMenuDao;
    @Autowired
    TaUserService taUserService;
    @Override
    public List<TaRoleMenu> selectRoleMenus() {
        List<TaRoleMenu> list = taRoleMenuDao.selectByExample(new TaRoleMenuExample());
        log.info("查询所有role-menu数据成功");
        return list;
    }
    @Override
    public List<TaRole> selectRoles() {
        List<TaRole> list = taRoleDao.selectByExample(new TaRoleExample());
        log.info("查询所有role数据成功");
        return list;
    }

    @Override
    public List<TaRole> selectRolesByUsername(String username) {
        //查出对应的tauser
        TaUser taUser=taUserService.selectUserByUsername(username);
        //查出user-role数据
        TaUserRole taUserRole=taUserService.selectUserRoleByUserId(taUser.getUserId());
        //根据roleid查出对应的role集合
        TaRoleExample taRoleExample=new TaRoleExample();
        taRoleExample.createCriteria().andRoleIdEqualTo(taUserRole.getRoleId());

        return taRoleDao.selectByExample(taRoleExample);
    }

    @Override
    public TaRoleMenu selectRoleMenuByRoleId(Long roleId) {
        TaRoleMenuExample taRoleMenuExample = new TaRoleMenuExample();
        taRoleMenuExample.createCriteria().andRoleIdEqualTo(roleId);
        List<TaRoleMenu> list = taRoleMenuDao.selectByExample(taRoleMenuExample);
        TaRoleMenu taRoleMenu = list.get(0);
        log.info("查询单个数据的结果为 :" + taRoleMenu);
        return taRoleMenu;
    }

    @Override
    public int insertRole(TaRole taRole, TaRoleMenu taRoleMenu) {
        int i = -999;
        if (taRole.getRoleName() != null && taRoleMenu.getMenuId() != null) {
            taRole.setCreateTime(System.currentTimeMillis());
            taRole.setModifyTime(System.currentTimeMillis());
            taRoleDao.insertSelective(taRole);
            Long roleId = taRole.getRoleId();
            log.info("增加的角色id为 :" + roleId);
            taRoleMenu.setRoleId(roleId);
            i = taRoleMenuDao.insertSelective(taRoleMenu);
            log.info("增加的权限角色关联数据的Id为 :" + taRoleMenu.getMenuId());
            return i;
        }
        log.error("增加role和role-menu失败");
        return i;
    }

    @Override
    public int deleteRole(Long roleId) {
        int i = -999;
        if (roleId > 0) {
            TaRoleExample taRoleExample = new TaRoleExample();
            taRoleExample.createCriteria().andRoleIdEqualTo(roleId);
            taRoleDao.deleteByExample(taRoleExample);
            log.info("删除的角色表数据id为 :" + roleId);

            TaRoleMenuExample taRoleMenuExample = new TaRoleMenuExample();
            taRoleMenuExample.createCriteria().andRoleIdEqualTo(roleId);
            i = taRoleMenuDao.deleteByExample(taRoleMenuExample);
            log.info("删除的角色权限表数据中的roleId为" + roleId);
            return i;
        }
        return i;
    }

    @Override
    public int updateRoleMenu(Long roleId, TaRoleMenu taRoleMenu) {
        int i = -999;
        if (roleId > 0 && taRoleMenu.getMenuId() > 0) {
            TaRoleMenuExample taRoleMenuExample = new TaRoleMenuExample();
            taRoleMenuExample.createCriteria().andRoleIdEqualTo(roleId);
            i = taRoleMenuDao.updateByExampleSelective(taRoleMenu, taRoleMenuExample);
            return i;
        }
        return i;
    }
}
