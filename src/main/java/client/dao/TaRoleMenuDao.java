package client.dao;


import client.pojo.TaRoleMenu;
import client.pojo.example.TaRoleMenuExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaRoleMenuDao {
    long countByExample(TaRoleMenuExample example);

    int deleteByExample(TaRoleMenuExample example);

    int insert(TaRoleMenu record);

    int insertSelective(TaRoleMenu record);

    List<TaRoleMenu> selectByExample(TaRoleMenuExample example);

    int updateByExampleSelective(@Param("record") TaRoleMenu record, @Param("example") TaRoleMenuExample example);

    int updateByExample(@Param("record") TaRoleMenu record, @Param("example") TaRoleMenuExample example);
}