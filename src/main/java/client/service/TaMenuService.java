package client.service;


import client.pojo.TaMenu;

import java.util.List;
import java.util.Set;


public interface TaMenuService {

    /**查询菜单信息
     * @param userName
     * @return
     */
    List<TaMenu> findUserMenus(String userName);


    /**查询用户权限
     * @param userName
     * @return
     */
    String findUserPermissions(String userName);

    /**查询用户权限
     * @param userName
     * @return 结果为权限集
     */
    Set<String> listUserPermissions(String userName);
}
