package com.huzd.huji.mapper;

import com.huzd.huji.pojo.Result;
import com.huzd.huji.pojo.connect;
import com.huzd.huji.pojo.huji;
import com.huzd.huji.pojo.loginRecord;
import com.huzd.huji.pojo.user;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface userMapper {

    user selectbyUsername(String Username);

    user select(user user);

    void add(user user);

    @Select("select * from usertable;")
    List<user> selectAll();

    @Select("select * from usertable where id = #{id};")
    user selectbyId(int id);

    @Insert("INSERT into loginrecord (username,loginTime) values (#{username},#{loginTime})")
    void addLoginRecord(loginRecord loginRecord);

    @Select("select * from loginrecord ORDER BY id DESC LIMIT 5;")
    List<loginRecord>selectAllLogin();

    @Select("select * from loginrecord where username LIKE #{Username}  ORDER BY id DESC LIMIT 5;")
    List<loginRecord>selectUserLogin(user user);

    @Update("UPDATE usertable set password = #{Password} where Username = #{Username}")
    void update(user user);


    //以下是关联户籍与用户的操作,为方便与用户一起统一管理
    @Insert("insert into connecttable (Username,Name,identityID) values (#{Username},#{Name},#{identityID})")
    void connectWithHuji(connect connect);

    @Select("select * from connecttable where Username = #{Username};")
    connect connectSelect(connect connect);

    @Update("UPDATE connecttable set Name = #{Name},identityID = #{identityID} where Username = #{Username}")
    void connectUpdate(connect connect);

    huji showHuji(user user);

    //以下是用户管理操作

    //    批量删除
    void deleteByIds(@Param("ids") int[]ids);

    //    分页查询
    List<user>selectByPage(@Param("begin") int index,@Param("size") int pageSize);

    //    数据总条目数
    int selectTotalCount();

    //    分页条件查询
    List<user>selectByPageWithCondition(@Param("begin") int index,@Param("size") int pageSize,@Param("user") user user);

    //    数据总条目数
    int selectTotalCountWithCondition(@Param("user") user user);

    //  查询密码
    @Select("select * from usertable where Username = #{user.Username}")
    user GetPasswd(@Param("user") user user);


}
