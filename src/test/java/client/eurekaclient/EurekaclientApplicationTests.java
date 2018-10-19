package client.eurekaclient;

import client.EurekaclientApplication;
import client.pojo.*;
import client.dao.UserMapper;
import client.service.TaMenuService;
import client.service.TaMenuServiceImp;
import client.service.TaUserService;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EurekaclientApplication.class)
public class EurekaclientApplicationTests {

    @Autowired(required = false)
    UserMapper userMapper;
    @Test
    public void contextLoads() {
        User user=userMapper.findByUserName("mrbird");
        System.out.println(user);
    }
    @Autowired
    private TaMenuService menuService;
    @Test
    public void test111() {
        Set<String> set=menuService.listUserPermissions("admin");
        System.out.println(set);

//        set.forEach(System.out::println);
//         String str=menuService.findUserPermissions("admin");
//        System.out.println(str);
    }
    @Test
    public void test133() {
        List<TaMenu> list=menuService.findUserMenus("admin");
//        list.forEach(System.out::println);
//        List list1=list.stream().map(TaMenu::getPerms).collect(Collectors.toList());
//        list1.forEach(System.out::println);
        TaRoleMenu taRoleMenu=new TaRoleMenu();
        taRoleMenu.setMenuId(9L);
        long l=9L;
        List list2=list.stream()
                .filter(taMenu -> taMenu.getMenuId()==l)
                .collect(Collectors.toList());
        System.out.println(list2);
    }
    @Autowired
    TaUserService taUserService;
//    @Test
//    public void test222() {
//        TaUser taUser=new TaUser();
//        taUser.setUsername("usershiro");
//        taUser.setPassword("123456");
//        taUser.setStatus("1");
//        TaUserRole taUserRole=new TaUserRole();
//        taUserRole.setRoleId(1L);
//        System.out.println(taUserService.insertUser(taUser,taUserRole));
//    }

}
