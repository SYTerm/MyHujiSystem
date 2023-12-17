package com.huzd.huji.Controller;

import com.huzd.huji.Service.noticeService;
import com.huzd.huji.pojo.Result;
import com.huzd.huji.pojo.huji;
import com.huzd.huji.pojo.notice;
import com.huzd.huji.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class noticeController {

    @Autowired
    private noticeService noticeService;


    public List<notice> selectUser(@RequestBody user user){
        return noticeService.selectUser(user);
    };
    @PostMapping("/byPage")
    public Map<String,Object>selectUserByPage(@RequestParam int currentPage,@RequestParam int pageSize, @RequestBody user user){

        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        List<notice> pageData = noticeService.selectUserByPage(index, pageSize,user);
        Integer totalCount = noticeService.selectTotalCount(user);
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };
//    @PostMapping("/add")
    public Result addNotice(@RequestBody notice notice){
        return noticeService.addNotice(notice);
    };
    @PostMapping("/addGood")
    public Result addGood(@RequestBody notice notice){
        return noticeService.addGoodNotice(notice);
    }
    @PostMapping("/addBad")
    public Result addBad(@RequestBody notice notice){
        return noticeService.addBadNotice(notice);
    }
}
