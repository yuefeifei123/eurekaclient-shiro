package client.shiro;

import client.dao.UserMapper;
import client.pojo.TaRole;
import client.pojo.TaUser;
import client.pojo.User;
import client.service.TaMenuService;
import client.service.TaRoleService;
import client.service.TaUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private TaUserService taUserService;
    @Autowired
    private TaRoleService taRoleService;
    @Autowired
    private TaMenuService taMenuService;

    /**
     * 获取用户角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        TaUser user = (TaUser) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUsername();

        System.out.println("用户名:" + userName +
                "***开始获取权限-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        List<TaRole> roleList = taRoleService.selectRolesByUsername(userName);
        Set<String> roleSet = new HashSet<String>();
        for (TaRole r : roleList) {
            roleSet.add(r.getRoleName());
        }
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        Set<String> permissionSet = taMenuService.listUserPermissions(userName);
        System.out.println("***用户:"+userName+"***权限为:"+permissionSet);
//        Set<String> permissionSet = new HashSet<String>();
//        for (Permission p : permissionList) {
//            permissionSet.add(p.getName());
//        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }
    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        System.out.println("-----用户名:" + userName + "*****password:"+password+
                "开始认证-----ShiroRealm.doGetAuthenticationInfo");

        // 通过用户名到数据库查询用户信息
        TaUser taUser = taUserService.selectUserByUsername(userName);

        if (taUser == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(taUser.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if (taUser.getStatus().equals("0")) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(taUser, password, getName());
        return info;
    }
}
