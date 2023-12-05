package com.huzd.huji.Controller;

import com.huzd.huji.Service.hujiService;
import com.huzd.huji.mapper.hujiMapper;

import com.huzd.huji.pojo.huji;
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
}
