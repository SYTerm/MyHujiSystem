package com.huzd.huji.Controller;

import com.huzd.huji.Service.idCardService;
import com.huzd.huji.pojo.Result;
import com.huzd.huji.pojo.connect;
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

    /*以下是身份证请求操作*/
    //    查询所有
    @GetMapping("/All")
    public List<idCard> selectAll(){
        return idCardService.selectAll();
    };

    @PostMapping("/updateTime")
    public Result updateTime(@RequestBody idCard idCard){
        idCardService.updateTime(idCard);
        return Result.success();
    }

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

    //    分页条件查询ForUser
    @PostMapping("/byPageCondUsr")
    public Map<String , Object> selectByPageWithConditionForUser(@RequestParam int currentPage,@RequestParam int pageSize,@RequestBody connect connect){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;

        List<idCard> pageData = idCardService.selectByPageWithConditionForUser(index, pageSize,connect);
        Integer totalCount = idCardService.selectTotalCountWithConditionForUser(connect);
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

    /*以下是身份证操作*/
    //    查询所有
    @GetMapping("/All2")
    public List<idCard> selectAll2(){
        return idCardService.selectAll2();
    };

    //    添加身份证记录
    @PostMapping("/add2")
    public String addidCard2(@RequestBody idCard idCard){
        idCardService.addidCard2(idCard);
        return "success";
    };

    //    分页查询
    @GetMapping("/byPage2")
    public Map<String , Object> selectByPage2(@RequestParam int currentPage,@RequestParam int pageSize)
    {
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        List<idCard> pageData = idCardService.selectByPage2(index, pageSize);
        Integer totalCount = idCardService.selectTotalCount2();
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    @GetMapping("/byPageAllRight2")
    public Map<String , Object> selectByPageAllRight2(@RequestParam int currentPage,@RequestParam int pageSize)
    {
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        List<idCard> pageData = idCardService.selectByPageAllRight2(index, pageSize);
        Integer totalCount = idCardService.selectTotalCountAllRight2();
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    //    数据总条目数
    public int selectTotalCount2(){
        return idCardService.selectTotalCount2();
    };

    //    分页条件查询
    @PostMapping("/byPageCond2")
    public Map<String , Object> selectByPageWithCondition2(@RequestParam int currentPage,@RequestParam int pageSize,@RequestBody idCard idCard){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        idCard.setName("%"+idCard.getName()+"%");
        idCard.setIdentityID("%"+idCard.getIdentityID()+"%");
        idCard.setStatus("%"+idCard.getStatus()+"%");

        List<idCard> pageData = idCardService.selectByPageWithCondition2(index, pageSize,idCard);
        Integer totalCount = idCardService.selectTotalCountWithCondition2(idCard);
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    //    分页条件查询ForUser
    @PostMapping("/byPageCondUsr2")
    public Map<String , Object> selectByPageWithConditionForUser2(@RequestParam int currentPage,@RequestParam int pageSize,@RequestBody connect connect){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;

        List<idCard> pageData = idCardService.selectByPageWithConditionForUser2(index, pageSize,connect);
        Integer totalCount = idCardService.selectTotalCountWithConditionForUser2(connect);
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };


    //    数据总条目数
    public int selectTotalCountWithCondition2(idCard idCard){
        return idCardService.selectTotalCountWithCondition2(idCard);
    };

    //    修改数据
    @PostMapping("/update2")
    public String updateidCard2(@RequestBody idCard idCard){
        if(idCard.getTypes().equals("注册"))
        {
            Result res =idCardService.addOrAdjustidCard2(idCard);
            idCardService.updateidCard(idCard);
            return res.getMsg();
        }
        else
        {
            idCardService.setStatus(idCard);
            idCardService.updateidCard(idCard);
            return "success";
        }

    };

    @PostMapping("/addidCards2")
    public Result addOrAdjustidCard2(@RequestBody idCard idCard){

        return idCardService.addOrAdjustidCard2(idCard);
    };

    //    删除数据
    @DeleteMapping("/delete2")
    public String deleteidCard2(@RequestBody int []ids){
        idCardService.deleteidCard2(ids);
        return "success";
    };

}
