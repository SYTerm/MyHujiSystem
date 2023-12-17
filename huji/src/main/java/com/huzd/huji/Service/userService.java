package com.huzd.huji.Service;

import com.huzd.huji.mapper.userMapper;
import com.huzd.huji.pojo.connect;
import com.huzd.huji.pojo.huji;
import com.huzd.huji.pojo.loginRecord;
import com.huzd.huji.pojo.user;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class userService {
    @Autowired
    private userMapper userMapper;

    public user select( user user){
        return userMapper.select(user);
    }

    public user selectByName(user user){
        return userMapper.selectbyUsername(user.getUsername());
    }

    public List<user> selectAll(){
        return userMapper.selectAll();
    }

    public void add( user user){
        userMapper.add(user);
    }

    public user selectById(int id){return userMapper.selectbyId(id);}

    public void addLoginRecord(loginRecord loginRecord){
        userMapper.addLoginRecord(loginRecord);
    }

    public List<loginRecord>selectAllLogin(){
        return userMapper.selectAllLogin();
    }

    public List<loginRecord>selectUserLogin(user user){return userMapper.selectUserLogin(user);}

    public void update(user user){userMapper.update(user);}

   //以下是绑定户籍操作
    public void connectWithHuji(connect connect){
        userMapper.connectWithHuji(connect);
    }

    public connect connectSelect(connect connect){return  userMapper.connectSelect(connect);}

    public void connectUpdate(connect connect){userMapper.connectUpdate(connect);}

    public huji showHuji(user user){return userMapper.showHuji(user);}

    //以下是用户管理操作
    //    批量删除
    public String deleteByIds(int[]ids){
        userMapper.deleteByIds(ids);
        return "success";
    };

    //    分页查询
    public List<user>selectByPage(int index,int pageSize){
        return userMapper.selectByPage(index,pageSize);
    };

    //    数据总条目数
    public int selectTotalCount(){
        return userMapper.selectTotalCount();
    };

    //    分页条件查询
    public List<user>selectByPageWithCondition(int index,int pageSize,user user){
        return userMapper.selectByPageWithCondition(index,pageSize,user);
    };

    //    数据总条目数
    public int selectTotalCountWithCondition(user user){
        return userMapper.selectTotalCountWithCondition(user);
    };

    //获取密码
    public user GetPasswd(user user){
        return userMapper.GetPasswd(user);
    };

}


