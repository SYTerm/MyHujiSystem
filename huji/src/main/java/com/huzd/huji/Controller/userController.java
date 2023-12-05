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
        if(user.getUsername()==null || user.getPassword()==null){
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
    public Result update(@RequestBody user user){
        if(user.getUsername()==null || user.getPassword()==null){
            return Result.err();
        }
        else if(userService.selectByName(user) != null)//查询后存在用户
        {
            userService.update(user);
            return Result.success();
        }
        else
        {
            return Result.err("用户不存在");
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
}
