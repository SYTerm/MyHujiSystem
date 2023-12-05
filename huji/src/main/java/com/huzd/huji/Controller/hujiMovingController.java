package com.huzd.huji.Controller;

import com.huzd.huji.Service.hujiMovingService;
import com.huzd.huji.pojo.Result;
import com.huzd.huji.pojo.huji;
import com.huzd.huji.pojo.hujiFormUpd;
import com.huzd.huji.pojo.hujiMoving;
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


}
