package client.dao;

import client.pojo.TaRole;
import client.pojo.example.TaRoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaRoleDao {
    long countByExample(TaRoleExample example);

    int deleteByExample(TaRoleExample example);

    int insert(TaRole record);

    int insertSelective(TaRole record);

    List<TaRole> selectByExample(TaRoleExample example);

    int updateByExampleSelective(@Param("record") TaRole record, @Param("example") TaRoleExample example);

    int updateByExample(@Param("record") TaRole record, @Param("example") TaRoleExample example);
}