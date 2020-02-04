package com.leifeng.community.mapper;

import com.leifeng.community.model.Community_Question;
import com.leifeng.community.model.Community_User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by ThinkPad on 2020/1/28.
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into community_question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) " +
            "values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{comment_count},#{view_count},#{like_count},#{tag})")
    public void create(Community_Question community_question);


}
