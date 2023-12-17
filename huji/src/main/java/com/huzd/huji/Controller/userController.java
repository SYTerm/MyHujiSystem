package com.huzd.huji.Controller;

import com.huzd.huji.Service.hujiService;
import com.huzd.huji.Service.userService;
import com.huzd.huji.Utils.TokenUtils;
import com.huzd.huji.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService userService;

    @Autowired
    private hujiService hujiService;

    //以下是登录操注册作

    @PostMapping("/login")
     public Result login(@RequestBody user user){
        if(user.getUsername()==null || user.getPassword()==null){
            return Result.err();
        }
        else if(userService.select(user)==null)
            return Result.err("用户名或密码错误");
        else
        {
            user user2 = userService.select(user);
            String token = TokenUtils.getToken(Integer.toString(user2.getId()), user2.getPassword());
            user2.setToken(token);

            //记录登陆时间
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            loginRecord loginRecord = new loginRecord();
            loginRecord.setUsername(user.getUsername());
            loginRecord.setLoginTime(dateFormat.format(date));

            userService.addLoginRecord(loginRecord);
            user2.setPassword("No!");
            user2.setId(0);

            return Result.success(user2);

        }

    }

    @PostMapping("/register")
    public Result register(@RequestBody user user){
        if(user.getUsername()==null || user.getUsername()=="" || user.getPassword()==null || user.getPassword()==""){
            return Result.err();
        }
        else if(userService.selectByName(user) == null)//查询后不存在用户
        {
            userService.add(user);
            return Result.success();
        }
        else
        {
            return Result.err("用户已存在");
        }

    }

    @PostMapping("/update")
    public Result update(@RequestParam String PasswdIn,@RequestBody user user){
        if(user.getUsername()==null || user.getPassword()==null){
            return Result.err();
        }
        user u2 = userService.selectByName(user);
         if(u2 != null && u2.getPassword().equals(PasswdIn))//查询后存在用户
        {
            userService.update(user);
            return Result.success();
        }
        else
        {
            return Result.err("旧密码错误或用户不存在");
        }

    }

    @PostMapping("/showPS")
    public user showPsw(@RequestBody user user){
        return userService.selectByName(user);
    }

    @GetMapping("/All")
    public List<user> selectAll(){
        return userService.selectAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody user user){
        userService.add(user);
    }

    //记录登录
    @PostMapping("/loginRecord")
    public List<loginRecord>loginRe(@RequestBody user user){
        //System.out.println(user.getUsername());
        if(user.getUsername().equals("admin"))
        return userService.selectAllLogin();
        else
            return userService.selectUserLogin(user);
    }

    //关联户籍
    @PostMapping("/ConnectHuji")
    public Result connectWithHuji(@RequestBody connect connect){
        if(connect.getIdentityID() == null ||connect.getName() == null || connect.getUsername() == null){
            return Result.err();
        }

        user user = new user();
        user.setUsername(connect.getUsername());

        huji huji = new huji();
        huji.setName(connect.getName());
        huji.setIdentityID(connect.getIdentityID());

        if(userService.selectByName(user) != null && hujiService.selectByNameAndId(huji) != null)//查询后不存在用户
        {
             if(userService.connectSelect(connect) == null)

                userService.connectWithHuji(connect);//不存在则新建
            else
                userService.connectUpdate(connect);//存在就更新

            return Result.success();
        }
        else
        {
            return Result.err("用户或户籍不存在");
        }
    }

    @PostMapping("/showHuji")
    public Result showHuji(@RequestBody user user){
        huji huji = userService.showHuji(user);
        return Result.success(huji);
    }

    //以下是用户管理操作

    @PostMapping("/GetPasswd")
    public Result getPaswd(@RequestBody user user){
        user u2 = userService.GetPasswd(user);
        return Result.success(u2);
    }

    @DeleteMapping("/deletes")
    public String deleteSomeUser(@RequestBody int []ids){
        userService.deleteByIds(ids);
        return "success";
    }

    @GetMapping("/byPage")
    public Map<String , Object> selectByPage(@RequestParam int currentPage, @RequestParam int pageSize){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        List<user> pageData = userService.selectByPage(index, pageSize);
        Integer totalCount = userService.selectTotalCount();
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };

    @PostMapping("/byPageCond")
    public Map<String , Object> selectByPageWithCondition(@RequestParam int currentPage, @RequestParam int pageSize,@RequestBody user user){
        //计算index和pageSize
        int index=(currentPage-1)*pageSize;
        user.setUsername("%"+user.getUsername()+"%");

        List<user> pageData = userService.selectByPageWithCondition(index, pageSize,user);
        Integer totalCount = userService.selectTotalCountWithCondition(user);
        //通过Map封装
        Map<String,Object> res=new HashMap<>();
        res.put("pageData",pageData);
        res.put("totalCount",totalCount);
        return res;
    };
}
