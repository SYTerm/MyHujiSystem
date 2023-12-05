package com.huzd.huji.mapper;

import com.huzd.huji.pojo.huji;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface hujiMapper {
//    查询所有
    List<huji>selectAll();

//    更新id
    void updateID();

//    添加户籍
    void addHuji(huji huji);

//    条件查询
    huji selectByNameAndId(huji huji);

//    批量删除
    void deleteByIds(@Param("ids") int[]ids);

//    分页查询
    List<huji>selectByPage(@Param("begin") int index,@Param("size") int pageSize);

//    数据总条目数
    int selectTotalCount();

//    分页条件查询
    List<huji>selectByPageWithCondition(@Param("begin") int index,@Param("size") int pageSize,@Param("huji") huji huji);

//    数据总条目数
    int selectTotalCountWithCondition(@Param("huji") huji huji);

//    修改数据
    void updateHuji(@Param("huji") huji huji);

    void updateHuji2(@Param("huji") huji huji);
}
