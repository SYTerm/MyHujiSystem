package com.huzd.huji.mapper;

import com.huzd.huji.pojo.connect;
import com.huzd.huji.pojo.idCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface idCardMapper {

/*
    以下是身份证办理操作
*/
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

    //    分页条件查询FORUSER
    List<idCard>selectByPageWithConditionForUser(@Param("begin") int index, @Param("size") int pageSize, @Param("connect")connect connect);


    //    数据总条目数
    int selectTotalCountWithCondition(@Param("idCard") idCard idCard);

    //    数据总条目数ForUser
    int selectTotalCountWithConditionForUser(@Param("connect") connect connect);

    //    修改数据
    void updateidCard(@Param("idCard") idCard idCard);

    // 查询办理完成的数据
    List<idCard>selectByPageAllRright(@Param("begin") int index,@Param("size") int pageSize);

    // 查询办理完成的数据
    int selectTotalCountAllRight();


/*
    以下是身份证操作
*/
    @Update("update idcardtable set status = '未过期' where identityID = #{idCard.identityID}")
    void setStatus(@Param("idCard") idCard idCard);
    @Select("select status from idcardtable")
    idCard selectStatus();
    //    查询所有
    List<idCard> selectAll2();
    //    查询一个
    idCard selectOne2(idCard idCard);

    //    添加身份证记录
    void addidCard2(idCard idCard);

    void addOrAdjustidCard2(idCard idCard);

    //更新时间
    void updateTime(@Param("idCard") idCard idCard);

    //删除身份证
    void deleteidCard2(@Param("ids") int[]ids);

    //    分页查询
    List<idCard>selectByPage2(@Param("begin") int index,@Param("size") int pageSize);

    //    数据总条目数
    int selectTotalCount2();

    //    分页条件查询
    List<idCard>selectByPageWithCondition2(@Param("begin") int index,@Param("size") int pageSize,@Param("idCard") idCard idCard);

    //    分页条件查询FORUSER
    List<idCard>selectByPageWithConditionForUser2(@Param("begin") int index, @Param("size") int pageSize, @Param("connect")connect connect);


    //    数据总条目数
    int selectTotalCountWithCondition2(@Param("idCard") idCard idCard);

    //    数据总条目数ForUser
    int selectTotalCountWithConditionForUser2(@Param("connect") connect connect);

    //    修改数据
    void updateidCard2(@Param("idCard") idCard idCard);

    // 查询办理完成的数据
    List<idCard>selectByPageAllRright2(@Param("begin") int index,@Param("size") int pageSize);

    // 查询办理完成的数据
    int selectTotalCountAllRight2();
}
