package client.dao;

import client.pojo.TaUserRole;
import client.pojo.example.TaUserRoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaUserRoleDao {
    long countByExample(TaUserRoleExample example);

    int deleteByExample(TaUserRoleExample example);

    int insert(TaUserRole record);

    int insertSelective(TaUserRole record);

    List<TaUserRole> selectByExample(TaUserRoleExample example);

    int updateByExampleSelective(@Param("record") TaUserRole record, @Param("example") TaUserRoleExample example);

    int updateByExample(@Param("record") TaUserRole record, @Param("example") TaUserRoleExample example);
}