package com.huzd.huji.Controller;

import com.huzd.huji.Service.hujiService;
import com.huzd.huji.Service.noticeService;
import com.huzd.huji.mapper.hujiMapper;

import com.huzd.huji.pojo.Result;
import com.huzd.huji.pojo.huji;
import com.huzd.huji.pojo.queryData;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/huji")
public class hujiController {

    @Autowired
    private hujiService hujiService;

    @GetMapping("/All")
    public List<huji>selectAllHuji(){
        return hujiService.selectAll();
    };

    @PostMapping("/add")
    public String addHuji(@RequestBody huji huji){
        hujiService.addHuji(huji);
        return "success";
    };

    @PostMapping("/update")
    public String updateHuji(@RequestBody huji huji){
        hujiService.updateHuji2(huji);
        return "success";
    }

    @DeleteMapping("/deletes")
    public String deleteSomeHuji(@RequestBody int []ids){
        hujiService.deleteByIds(ids);
        return "success";
    }

    @GetMapping("/byPage")
    public Map<String , Object> selectByPage(@RequestParam int currentPage, @RequestParam int pageSize){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        List<huji> pageData = hujiService.selectByPage(index, pageSize);
        Integer totalCount = hujiService.selectTotalCount();
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    @PostMapping("/byPageCond")
    public Map<String , Object> selectByPageWithCondition(@RequestParam int currentPage, @RequestParam int pageSize,@RequestBody huji huji){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        huji.setName("%"+huji.getName()+"%");
        huji.setIdentityID("%"+huji.getIdentityID()+"%");
        huji.setLocation("%"+huji.getLocation()+"%");

        List<huji> pageData = hujiService.selectByPageWithCondition(index, pageSize,huji);
        Integer totalCount = hujiService.selectTotalCountWithCondition(huji);
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    //    用户提交数据操作
    @PostMapping("/usrUpload")
    public Result upload(@RequestBody queryData queryData){
        return  hujiService.upload(queryData);
    };

    //    以下是管理员操纵用户提交数据操作
    @GetMapping("/hujiQuery")
    public List<queryData> showHUjiQuery(){
        return hujiService.showHUjiQuery();
    };

    //分页查询
    @GetMapping("/byPageQuery")
    public Map<String , Object> selectQueryByPage(@RequestParam int currentPage, @RequestParam int pageSize){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        List<queryData> pageData = hujiService.selectQueryByPage(index, pageSize);

        Integer totalCount = hujiService.selectTotalCountQuery();
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    //分页条件查询
    @PostMapping("/byPageCondQuery")
    public Map<String , Object> selectQueryByPageWithCondition(@RequestParam int currentPage, @RequestParam int pageSize,@RequestBody queryData queryData){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        queryData.setUsername("%"+queryData.getUsername()+"%");
        queryData.setName("%"+queryData.getName()+"%");

        List<queryData> pageData = hujiService.selectQueryByPageCond(index, pageSize,queryData);
        Integer totalCount = hujiService.selectTotalCountQueryByPageCond(queryData);
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    //提交请求到户籍表并清除请求
    @PostMapping("/QueryReal")
    public Result uploadQuery(@RequestBody queryData queryData){
        huji huji = new huji();
        huji.setName(queryData.getName());
        huji.setIdentityID(queryData.getIdentityID());
        huji.setAge(queryData.getAge());
        huji.setSex(queryData.getSex());
        huji.setPhoneNumber(queryData.getPhoneNumber());
        huji.setLocation(queryData.getLocation());
        hujiService.addHuji(huji);
        hujiService.deleteHujiQuery(queryData);
        return Result.success();
    }

    //删除请求
    @DeleteMapping("/delQuery")
    public Result deleteHujiQuery(@RequestBody int[] ids){
        hujiService.deleteHujiQuery(ids);
        return Result.success();
    };

}
