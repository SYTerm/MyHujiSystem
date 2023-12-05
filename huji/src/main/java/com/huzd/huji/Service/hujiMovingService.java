package com.huzd.huji.Service;

import com.huzd.huji.mapper.hujiMapper;
import com.huzd.huji.mapper.hujiMovingMapper;
import com.huzd.huji.pojo.Result;
import com.huzd.huji.pojo.huji;
import com.huzd.huji.pojo.hujiFormUpd;
import com.huzd.huji.pojo.hujiMoving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class hujiMovingService {

    @Autowired
    private hujiMovingMapper hujiMovingMapper;
    @Autowired
    private hujiMapper hujiMapper;

    public List<hujiMoving> selectByPage(int currentPage, int pageSize){
        return hujiMovingMapper.selectByPage(currentPage, pageSize);


    };

    public List<hujiMoving> selectByPageWithCondition( int currentPage, int pageSize,hujiMoving hujiMoving){


        return hujiMovingMapper.selectByPageWithCondition(currentPage, pageSize, hujiMoving);

    };

    public int selectTotalCount(){
        return hujiMovingMapper.selectTotalCount();
    }

    public int selectTotalCountWithCondition(hujiMoving hujiMoving){
        return hujiMovingMapper.selectTotalCountWithCondition(hujiMoving);
    }

    public Result addHujiMoving(hujiFormUpd hujiFormUpd){
        hujiMoving hujiMoving = new hujiMoving();
        huji huji = new huji();
        huji.setIdentityID(hujiFormUpd.getIdentityID());
        huji.setName(hujiFormUpd.getName());
        huji inhuji = hujiMapper.selectByNameAndId(huji);
        if(inhuji == null)
            return Result.err("用户信息错误或非注册在籍用户，请修改信息或先创建户籍");
        else
        {
            hujiMoving.setId(inhuji.getId());
            hujiMoving.setName(hujiFormUpd.getName());
            hujiMoving.setIdentityID(hujiFormUpd.getIdentityID());
            hujiMoving.setLocationNew(hujiFormUpd.getLocation());
            hujiMoving.setLocationOld(hujiFormUpd.getLocationOld());
            hujiMoving.setMovingTypes(hujiFormUpd.getMovingTypes());

            huji.setLocation(hujiFormUpd.getLocation());
            huji.setId(inhuji.getId());

            hujiMovingMapper.addHujiMoving(hujiMoving);
            hujiMapper.updateHuji(huji);
            return Result.success("户籍迁移成功");
        }

    }

}
