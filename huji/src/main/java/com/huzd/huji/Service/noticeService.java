package com.huzd.huji.Service;

import com.huzd.huji.mapper.noticeMapper;
import com.huzd.huji.pojo.Result;
import com.huzd.huji.pojo.notice;
import com.huzd.huji.pojo.user;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class noticeService {

    @Autowired
    private noticeMapper noticeMapper;

    public List<notice> selectUser(user user){
        return noticeMapper.selectUser(user);
    };

    public List<notice>selectUserByPage(int index,int size,user user){
        return noticeMapper.selectUserByPage(index,size,user);
    };

     public Result addNotice(notice notice){
         noticeMapper.addNotice(notice);
         return Result.success();
     };
     public int selectTotalCount(user user){
         return noticeMapper.selectTotalCount(user);
     };

    public Result addBadNotice(notice notice){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);
         notice.setInfo("管理员已拒绝您的请求:"+notice.getInfo());
         notice.setProcessingTime(formattedDate);
        noticeMapper.addNotice(notice);
        return Result.success();
    };

    public Result addGoodNotice(notice notice){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);
        notice.setInfo("管理员已处理您的请求:"+notice.getInfo());
        notice.setProcessingTime(formattedDate);
        noticeMapper.addNotice(notice);
        return Result.success();
    };
}
