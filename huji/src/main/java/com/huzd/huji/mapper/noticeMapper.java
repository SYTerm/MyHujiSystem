package com.huzd.huji.mapper;

import com.huzd.huji.pojo.notice;
import com.huzd.huji.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface noticeMapper {

    List<notice>selectUser(@Param("user") user user);

    List<notice>selectUserByPage(@Param("index") int index,@Param("size") int size,@Param("user") user user);

    void addNotice(@Param("notice")notice notice);

    int selectTotalCount(@Param("user") user user);
}
