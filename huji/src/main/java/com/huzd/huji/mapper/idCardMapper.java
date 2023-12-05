package com.huzd.huji.mapper;

import com.huzd.huji.pojo.idCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface idCardMapper {
    //    查询所有
    List<idCard> selectAll();
    //    查询一个
    idCard selectOne(idCard idCard);

    //    添加身份证记录
    void addidCard(idCard idCard);

    void addOrAdjustidCard(idCard idCard);

    //删除身份证
    void deleteidCard(@Param("ids") int[]ids);

    //    分页查询
    List<idCard>selectByPage(@Param("begin") int index,@Param("size") int pageSize);

    //    数据总条目数
    int selectTotalCount();

    //    分页条件查询
    List<idCard>selectByPageWithCondition(@Param("begin") int index,@Param("size") int pageSize,@Param("idCard") idCard idCard);

    //    数据总条目数
    int selectTotalCountWithCondition(@Param("idCard") idCard idCard);

    //    修改数据
    void updateidCard(@Param("idCard") idCard idCard);

    // 查询办理完成的数据
    List<idCard>selectByPageAllRright(@Param("begin") int index,@Param("size") int pageSize);

    // 查询办理完成的数据
    int selectTotalCountAllRight();
}
