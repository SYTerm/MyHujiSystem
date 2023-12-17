package com.huzd.huji.Controller;

import com.huzd.huji.Service.hujiMovingService;
import com.huzd.huji.pojo.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hujiMoving")
public class hujiMovingController {

    @Autowired
    private hujiMovingService hujiMovingService;

    @GetMapping("/byPage")
    public Map<String , Object> selectByPage(@RequestParam int currentPage, @RequestParam int pageSize){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        List<hujiMoving> pageData = hujiMovingService.selectByPage(index, pageSize);
        Integer totalCount = hujiMovingService.selectTotalCount();
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    @PostMapping("/byPageCond")
    public Map<String , Object> selectByPageWithCondition(@RequestParam int currentPage, @RequestParam int pageSize,@RequestBody hujiMoving hujiMoving){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        hujiMoving.setMovingTypes("%"+hujiMoving.getMovingTypes()+"%");

        List<hujiMoving> pageData = hujiMovingService.selectByPageWithCondition(index, pageSize,hujiMoving);
        Integer totalCount = hujiMovingService.selectTotalCountWithCondition(hujiMoving);
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    @PostMapping("/add")
    public Result addHujiMoving(@RequestBody hujiFormUpd hujiFormUpd){
       return hujiMovingService.addHujiMoving(hujiFormUpd);
    }

    @PostMapping("/details")
    public Result getDetail(@RequestBody hujiMoving hujiMoving){
        return hujiMovingService.GetDetails(hujiMoving);
    }

    //    用户提交数据操作
    @PostMapping("/usrUpload")
    public Result upload(@RequestBody hujiMovingqueryData queryData){
        return  hujiMovingService.upload(queryData);
    };

    //    以下是管理员操纵用户提交数据操作
    @GetMapping("/hujiQuery")
    public List<hujiMovingqueryData> showHUjiQuery(){
        return hujiMovingService.showHUjiQuery();
    };

    //分页查询
    @GetMapping("/byPageQuery")
    public Map<String , Object> selectQueryByPage(@RequestParam int currentPage, @RequestParam int pageSize){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        List<hujiMovingqueryData> pageData = hujiMovingService.selectQueryByPage(index, pageSize);

        Integer totalCount = hujiMovingService.selectTotalCountQuery();
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    //分页条件查询
    @PostMapping("/byPageCondQuery")
    public Map<String , Object> selectQueryByPageWithCondition(@RequestParam int currentPage, @RequestParam int pageSize,@RequestBody hujiMovingqueryData queryData){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        queryData.setUsername("%"+queryData.getUsername()+"%");
        queryData.setName("%"+queryData.getName()+"%");

        List<hujiMovingqueryData> pageData = hujiMovingService.selectQueryByPageCond(index, pageSize,queryData);
        Integer totalCount = hujiMovingService.selectTotalCountQueryByPageCond(queryData);
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    //提交请求到户籍表并清除请求
    @PostMapping("/QueryReal")
    public Result uploadQuery(@RequestBody hujiMovingqueryData queryData){
        hujiMovingService.setReal(queryData);
        hujiMovingService.deleteHujiQuery(queryData);
        return Result.success();
    }

    //删除请求
    @DeleteMapping("/delQuery")
    public Result deleteHujiQuery(@RequestBody int[] ids){
        hujiMovingService.deleteHujiQuery(ids);
        return Result.success();
    };

}
