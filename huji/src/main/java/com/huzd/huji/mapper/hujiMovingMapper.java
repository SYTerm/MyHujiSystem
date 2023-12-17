package com.huzd.huji.mapper;

import com.huzd.huji.pojo.hujiMoving;
import com.huzd.huji.pojo.hujiMovingqueryData;
import com.huzd.huji.pojo.queryData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface hujiMovingMapper {

   List<hujiMoving> selectAll();

   List<hujiMoving> selectByPage(@Param("begin") int index, @Param("size") int pageSize);

    int selectTotalCount();

    void addHujiMoving(hujiMoving hujiMoving);

    List<hujiMoving>selectByPageWithCondition(@Param("begin") int index, @Param("size") int pageSize, @Param("hujiMoving") hujiMoving hujiMoving);

    int selectTotalCountWithCondition(@Param("hujiMoving") hujiMoving hujiMoving);

    int selectTotalIn(@Param("hujiMoving") hujiMoving hujiMoving);

    int selectTotalOut(@Param("hujiMoving") hujiMoving hujiMoving);

    hujiMoving selectLastLocation(@Param("hujiMoving") hujiMoving hujiMoving);

    String selectStatus(@Param("hujiMoving") hujiMoving hujiMoving);

    //    以下是用户提交数据操作

    void upload(@Param("queryData") hujiMovingqueryData queryData);

    //    以下是管理员操纵用户提交数据操作
    @Select("select * from hujimovingquerytable")
    List<hujiMovingqueryData> showHUjiQuery();

    //分页查询
    List<hujiMovingqueryData>selectQueryByPage(@Param("begin") int index,@Param("size") int pageSize);

    //分页记录总数
    @Select("select count(*) from hujimovingquerytable")
    int selectTotalCountQuery();

    //分页条件查询
    List<hujiMovingqueryData>selectQueryByPageCond(@Param("begin") int index,@Param("size") int pageSize,@Param("queryData") hujiMovingqueryData queryData);
    //分页条件查询总数
    int selectTotalCountQueryByPageCond(@Param("queryData") hujiMovingqueryData queryData);

    @Delete("delete from hujiMovingquerytable where identityID = #{queryData.identityID}")
    void deleteHujiQuery(@Param("queryData") hujiMovingqueryData queryData);

    //    批量删除
    void deleteQueryByIds(@Param("ids") int[]ids);


}
