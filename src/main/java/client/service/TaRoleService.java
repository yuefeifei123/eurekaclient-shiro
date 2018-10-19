package client.service;


import client.pojo.TaRole;
import client.pojo.TaRoleMenu;

import java.util.List;

public interface TaRoleService {

    /**查询所有role-menu数据
     * @return
     */
    List<TaRoleMenu> selectRoleMenus();

    /**查询所有role数据
     * @return
     */
    List<TaRole> selectRoles();

    /**查询所有role数据
     * @return
     */
    List<TaRole> selectRolesByUsername(String username);

    /**查询单个role-menu数据
     * @param roleId
     * @return
     */
    TaRoleMenu selectRoleMenuByRoleId(Long roleId);

    /**插入role数据和role-menu数据
     * @param taRole
     * @param taRoleMenu
     * @return
     */
    int insertRole(TaRole taRole, TaRoleMenu taRoleMenu);

    /**删除role数据和role-menu数据
     * @param roleId
     * @return
     */
    int deleteRole(Long roleId);

    /**更新role和role-menu数据
     * @param roleId
     * @param taRoleMenu
     * @return
     */
    int updateRoleMenu(Long roleId, TaRoleMenu taRoleMenu);
}
