package com.leifeng.community.mapper;

import com.leifeng.community.model.Community_User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ThinkPad on 2020/1/25.
 */
@Mapper
public interface UserMapper {
    @Insert("insert into Community_User(account_id,name,token,gmt_create,gmt_modified) " +
            "values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified})")
    void insert(Community_User community_user);

    @Select("select * from community_user where token = #{token}")
    Community_User findByToken(@Param("token") String token);
}
