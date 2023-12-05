package com.huzd.huji.mapper;

import com.huzd.huji.pojo.hujiMoving;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    

}
