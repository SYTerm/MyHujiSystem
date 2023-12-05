package com.huzd.huji.Controller;

import com.huzd.huji.Service.idCardService;
import com.huzd.huji.pojo.Result;
import com.huzd.huji.pojo.huji;
import com.huzd.huji.pojo.idCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/idCards")
public class idCardController {
    
    @Autowired
    private idCardService idCardService;

    //    查询所有
    @GetMapping("/All")
    public List<idCard> selectAll(){
        return idCardService.selectAll();
    };

    //    添加身份证记录
    @PostMapping("/add")
    public String addidCard(@RequestBody idCard idCard){
        idCardService.addidCard(idCard);
        return "success";
    };

    //    分页查询
    @GetMapping("/byPage")
     public Map<String , Object> selectByPage(@RequestParam int currentPage,@RequestParam int pageSize)
    {
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        List<idCard> pageData = idCardService.selectByPage(index, pageSize);
        Integer totalCount = idCardService.selectTotalCount();
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    @GetMapping("/byPageAllRight")
    public Map<String , Object> selectByPageAllRight(@RequestParam int currentPage,@RequestParam int pageSize)
    {
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        List<idCard> pageData = idCardService.selectByPageAllRight(index, pageSize);
        Integer totalCount = idCardService.selectTotalCountAllRight();
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    //    数据总条目数
    public int selectTotalCount(){
        return idCardService.selectTotalCount();
    };

    //    分页条件查询
    @PostMapping("/byPageCond")
    public Map<String , Object> selectByPageWithCondition(@RequestParam int currentPage,@RequestParam int pageSize,@RequestBody idCard idCard){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        idCard.setName("%"+idCard.getName()+"%");
        idCard.setIdentityID("%"+idCard.getIdentityID()+"%");
        idCard.setStatus("%"+idCard.getStatus()+"%");

        List<idCard> pageData = idCardService.selectByPageWithCondition(index, pageSize,idCard);
        Integer totalCount = idCardService.selectTotalCountWithCondition(idCard);
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    //    数据总条目数
    public int selectTotalCountWithCondition(idCard idCard){
        return idCardService.selectTotalCountWithCondition(idCard);
    };

    //    修改数据
    @PostMapping("/update")
    public String updateidCard(@RequestBody idCard idCard){
        idCardService.updateidCard(idCard);
        return "success";
    };

    @PostMapping("/addidCards")
    public Result addOrAdjustidCard(@RequestBody idCard idCard){
        return idCardService.addOrAdjustidCard(idCard);
    };

    //    删除数据
    @DeleteMapping("/delete")
    public String deleteidCard(@RequestBody int []ids){
        idCardService.deleteidCard(ids);
        return "success";
    };
}
