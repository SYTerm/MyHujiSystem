package com.huzd.huji.Service;

import com.huzd.huji.mapper.idCardMapper;
import com.huzd.huji.pojo.Result;
import com.huzd.huji.pojo.idCard;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class idCardService {
    @Autowired
private idCardMapper idCardMapper;

    //    查询所有
   public List<idCard> selectAll(){
        return idCardMapper.selectAll();
    };

    //    添加身份证记录
    public String addidCard(idCard idCard){
         idCardMapper.addidCard(idCard);
         return "success";
    };

    public Result addOrAdjustidCard(idCard idCard){
        if(idCardMapper.selectOne(idCard) != null)
            return  Result.err("已存在用户不要重复注册");
        else
        {
            idCardMapper.addOrAdjustidCard(idCard);
            return  Result.success("办理成功");
        }

    };

    //删除
    public String deleteidCard(int[]ids){
        idCardMapper.deleteidCard(ids);
        return "success";
    }

    //    分页查询
    public List<idCard>selectByPage(int index, int pageSize)
    {
        return idCardMapper.selectByPage(index,pageSize);
    };

    //    数据总条目数
    public int selectTotalCount(){
        return idCardMapper.selectTotalCount();
    };

    //    分页条件查询
    public List<idCard>selectByPageWithCondition(int index, int pageSize, idCard idCard){
        return idCardMapper.selectByPageWithCondition(index,pageSize,idCard);
    };

    //    数据总条目数
    public int selectTotalCountWithCondition(idCard idCard){
        return idCardMapper.selectTotalCountWithCondition(idCard);
    };

    //    修改数据
    public String updateidCard(idCard idCard){
        idCardMapper.updateidCard(idCard);
        return "success";
    };

    public List<idCard> selectByPageAllRight(int index, int pageSize) {
        return idCardMapper.selectByPageAllRright(index,pageSize);
    }

    public Integer selectTotalCountAllRight() {
        return idCardMapper.selectTotalCountAllRight();
    }
}
