package client.dao;


import client.pojo.TaMenu;
import client.pojo.example.TaMenuExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaMenuDao {
    long countByExample(TaMenuExample example);

    int deleteByExample(TaMenuExample example);

    int insert(TaMenu record);

    int insertSelective(TaMenu record);

    List<TaMenu> selectByExample(TaMenuExample example);

    int updateByExampleSelective(@Param("record") TaMenu record, @Param("example") TaMenuExample example);

    int updateByExample(@Param("record") TaMenu record, @Param("example") TaMenuExample example);

    List<TaMenu> findUserMenus(String userName);

    List<TaMenu> findUserPermissions(String userName);


}