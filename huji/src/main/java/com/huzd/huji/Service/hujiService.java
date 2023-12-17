package com.huzd.huji.Service;

import com.huzd.huji.mapper.hujiMapper;
import com.huzd.huji.pojo.Result;
import com.huzd.huji.pojo.connect;
import com.huzd.huji.pojo.huji;
import com.huzd.huji.pojo.queryData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class hujiService {
    @Autowired
    private hujiMapper hujiMapper;

    public List<huji> selectAll(){
        return hujiMapper.selectAll();
    };

    public String addHuji( huji huji){
        hujiMapper.addHuji(huji);
        return "success";
    };

    public String updateHuji2( huji huji){
        hujiMapper.updateHuji2(huji);
        return "success";
    }

    public String deleteByIds( int []ids){
        hujiMapper.deleteByIds(ids);
        return "success";
    }

    public List<huji> selectByPage( int currentPage, int pageSize){
        return hujiMapper.selectByPage(currentPage, pageSize);


    };

    public List<huji> selectByPageWithCondition( int currentPage, int pageSize,@RequestBody huji huji){


        return hujiMapper.selectByPageWithCondition(currentPage, pageSize,huji);

    };

    public int selectTotalCount(){
        return hujiMapper.selectTotalCount();
    }

    public int selectTotalCountWithCondition(huji huji){
        return hujiMapper.selectTotalCountWithCondition(huji);
    }

    public huji selectByNameAndId(huji huji){return hujiMapper.selectByNameAndId(huji);}

    //    用户提交数据操作
   public Result upload(queryData queryData){
       huji huji = new huji();
       huji.setName(queryData.getName());
       huji.setIdentityID(queryData.getIdentityID());
       if(huji.getName() =="" || huji.getIdentityID() == "")
           return  Result.err("输入存在空值");
       else
       {
           //是否存在户籍
           if(hujiMapper.selectByNameAndId(huji) == null)
           {
               hujiMapper.upload(queryData);
               return Result.success();
           }
           else
               return  Result.err("户籍已存在");
       }

   };

    //    以下是管理员操纵用户提交数据操作
    public List<queryData> showHUjiQuery(){
        return hujiMapper.showHUjiQuery();
    };

    public Result deleteHujiQuery(queryData queryData){
        hujiMapper.deleteHujiQuery(queryData);
        return Result.success();
    };

    public Result deleteHujiQuery(int[] ids){
        hujiMapper.deleteQueryByIds(ids);
        return Result.success();
    };

    public List<queryData>selectQueryByPage(int index,int pageSize){
        return hujiMapper.selectQueryByPage(index,pageSize);
    };

    public int selectTotalCountQuery(){
        return hujiMapper.selectTotalCountQuery();
    }


    public List<queryData>selectQueryByPageCond(int index,int pageSize,queryData queryData){
        return hujiMapper.selectQueryByPageCond(index,pageSize,queryData);
    };

    public int selectTotalCountQueryByPageCond(queryData queryData){
        return  hujiMapper.selectTotalCountQueryByPageCond(queryData);
    };


}
