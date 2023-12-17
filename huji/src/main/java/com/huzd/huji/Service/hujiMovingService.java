package com.huzd.huji.Service;

import com.huzd.huji.mapper.hujiMapper;
import com.huzd.huji.mapper.hujiMovingMapper;
import com.huzd.huji.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class hujiMovingService {

    @Autowired
    private hujiMovingMapper hujiMovingMapper;
    @Autowired
    private hujiMapper hujiMapper;

    public List<hujiMoving> selectByPage(int currentPage, int pageSize){
        return hujiMovingMapper.selectByPage(currentPage, pageSize);


    };

    public List<hujiMoving> selectByPageWithCondition( int currentPage, int pageSize,hujiMoving hujiMoving){


        return hujiMovingMapper.selectByPageWithCondition(currentPage, pageSize, hujiMoving);

    };

    public int selectTotalCount(){
        return hujiMovingMapper.selectTotalCount();
    }

    public int selectTotalCountWithCondition(hujiMoving hujiMoving){
        return hujiMovingMapper.selectTotalCountWithCondition(hujiMoving);
    }

    public Result addHujiMoving(hujiFormUpd hujiFormUpd){
        hujiMoving hujiMoving = new hujiMoving();
        huji huji = new huji();
        huji.setIdentityID(hujiFormUpd.getIdentityID());
        huji.setName(hujiFormUpd.getName());
        huji inhuji = hujiMapper.selectByNameAndId(huji);
        if(inhuji == null)
            return Result.err("用户信息错误或非注册在籍用户，请修改信息或先创建户籍");
        else
        {
            hujiMoving.setId(inhuji.getId());
            hujiMoving.setName(hujiFormUpd.getName());
            hujiMoving.setIdentityID(hujiFormUpd.getIdentityID());
            hujiMoving.setLocationNew(hujiFormUpd.getLocation());
            hujiMoving.setLocationOld(hujiFormUpd.getLocationOld());
            hujiMoving.setMovingTypes(hujiFormUpd.getMovingTypes());

            huji.setLocation(hujiFormUpd.getLocation());
            huji.setId(inhuji.getId());

            hujiMovingMapper.addHujiMoving(hujiMoving);
            hujiMapper.updateHuji(huji);
            return Result.success("户籍迁移信息提交成功");
        }

    }

     public Result GetDetails(hujiMoving hujiMoving){
         Integer In = hujiMovingMapper.selectTotalIn(hujiMoving);
         Integer Out = hujiMovingMapper.selectTotalOut(hujiMoving);
         String Status = hujiMovingMapper.selectStatus(hujiMoving);
         hujiMoving Last = hujiMovingMapper.selectLastLocation(hujiMoving);
         if(Last == null){
             Last = new hujiMoving();
         }
         Map<String,Object>res = new HashMap<>();
         res.put("In",In);
         res.put("Out",Out);
         res.put("Status",Status);
         res.put("Last",Last);
         return Result.success(res);
     }

    //    用户提交数据操作
    public Result upload(hujiMovingqueryData queryData){
        huji huji = new huji();
        huji.setIdentityID(queryData.getIdentityID());
        huji.setName(queryData.getName());
        huji inhuji = hujiMapper.selectByNameAndId(huji);
        if(inhuji == null)
            return Result.err("用户信息错误或非注册在籍用户，请修改信息或先创建户籍");
        else
        {
            queryData.setId(inhuji.getId());
            hujiMovingMapper.upload(queryData);
            return Result.success("户籍迁移成功");
        }

    };

    //    以下是管理员操纵用户提交数据操作
    public List<hujiMovingqueryData> showHUjiQuery(){
        return hujiMovingMapper.showHUjiQuery();
    };

    public Result deleteHujiQuery(hujiMovingqueryData queryData){
        hujiMovingMapper.deleteHujiQuery(queryData);
        return Result.success();
    };

    public Result setReal(hujiMovingqueryData queryData){
        huji huji = new huji();
        hujiMoving hujiMoving = new hujiMoving();
        huji.setIdentityID(queryData.getIdentityID());
        huji.setName(queryData.getName());
        huji inhuji = hujiMapper.selectByNameAndId(huji);

            hujiMoving.setId(inhuji.getId());
            hujiMoving.setName(queryData.getName());
            hujiMoving.setIdentityID(queryData.getIdentityID());
            hujiMoving.setLocationNew(queryData.getLocationNew());
            hujiMoving.setLocationOld(queryData.getLocationOld());
            hujiMoving.setMovingTypes(queryData.getMovingTypes());

            huji.setLocation(queryData.getLocationNew());
            huji.setId(inhuji.getId());
        hujiMovingMapper.addHujiMoving(hujiMoving);
        hujiMapper.updateHuji(huji);
        return Result.success("户籍迁移成功");
    }

    public Result deleteHujiQuery(int[] ids){
        hujiMovingMapper.deleteQueryByIds(ids);
        return Result.success();
    };

    public List<hujiMovingqueryData>selectQueryByPage(int index,int pageSize){
        return hujiMovingMapper.selectQueryByPage(index,pageSize);
    };

    public int selectTotalCountQuery(){
        return hujiMovingMapper.selectTotalCountQuery();
    }


    public List<hujiMovingqueryData>selectQueryByPageCond(int index,int pageSize,hujiMovingqueryData queryData){
        return hujiMovingMapper.selectQueryByPageCond(index,pageSize,queryData);
    };

    public int selectTotalCountQueryByPageCond(hujiMovingqueryData queryData){
        return  hujiMovingMapper.selectTotalCountQueryByPageCond(queryData);
    };

}
