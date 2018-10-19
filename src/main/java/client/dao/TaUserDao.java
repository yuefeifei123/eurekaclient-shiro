package client.dao;


import client.pojo.TaUser;
import client.pojo.example.TaUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaUserDao {
    long countByExample(TaUserExample example);

    int deleteByExample(TaUserExample example);

    int insert(TaUser record);

    int insertSelective(TaUser record);

    List<TaUser> selectByExample(TaUserExample example);

    int updateByExampleSelective(@Param("record") TaUser record, @Param("example") TaUserExample example);

    int updateByExample(@Param("record") TaUser record, @Param("example") TaUserExample example);
}