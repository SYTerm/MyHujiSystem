package com.huzd.huji.Service;

import com.huzd.huji.mapper.idCardMapper;
import com.huzd.huji.pojo.Result;
import com.huzd.huji.pojo.connect;
import com.huzd.huji.pojo.idCard;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class idCardService {
    @Autowired
private idCardMapper idCardMapper;
/*以下是身份证请求操作*/
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
        if(idCard.getName() == null
           || idCard.getIdentityID() == null
                || idCard.getAge() == null
                || idCard.getSex() == null
                || idCard.getNation() == null
                || idCard.getCollectionTime() == null
                || idCard.getProcessingTime() == null
                || idCard.getStatus() == null
                || idCard.getTypes() == null
                ||idCard.getName() == ""
                || idCard.getIdentityID() == ""
                || idCard.getAge() == ""
                || idCard.getSex() == ""
                || idCard.getNation() == ""
                || idCard.getCollectionTime() == ""
                || idCard.getProcessingTime() == ""
                || idCard.getStatus() == ""
                || idCard.getTypes() == "")
            return Result.err();
        else
        {
            if(idCard.getTypes().equals("注册")){
                if(idCardMapper.selectOne2(idCard) != null)
                    return  Result.err("已存在用户不要重复注册");
                else
                {
                    idCardMapper.addOrAdjustidCard(idCard);
                    return  Result.success("办理成功");
                }
            }
            else if(idCard.getTypes().equals("更新")) {
                if(idCardMapper.selectOne2(idCard) == null)
                    return  Result.err("不存在用户");
                else
                {
                    idCardMapper.addOrAdjustidCard(idCard);
                    return  Result.success("办理成功");
                }
            }
            else
                return Result.err();
        }




    };


    public Result updateTime(idCard idCard){
        idCardMapper.updateTime(idCard);
        return Result.success();
    }

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

    //    分页条件查询FORUSER
    public List<idCard>selectByPageWithConditionForUser(int index, int pageSize, connect connect){
        return  idCardMapper.selectByPageWithConditionForUser(index,pageSize,connect);
    };


    //    数据总条目数
    public int selectTotalCountWithCondition(idCard idCard){
        return idCardMapper.selectTotalCountWithCondition(idCard);
    };

    //    数据总条目数ForUser
    public int selectTotalCountWithConditionForUser(connect connect){
        return idCardMapper.selectTotalCountWithConditionForUser(connect);
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

    /*以下是身份证操作*/
    //更新期限
    public Result setStatus(idCard idCard){
         idCardMapper.setStatus(idCard);
         return Result.success();
    };

    //    查询所有
    public List<idCard> selectAll2(){
        return idCardMapper.selectAll2();
    };

    //    添加身份证记录
    public String addidCard2(idCard idCard){
        idCardMapper.addidCard2(idCard);
        return "success";
    };

    public Result addOrAdjustidCard2(idCard idCard){
        if(idCardMapper.selectOne2(idCard) != null)
            return  Result.err("已存在用户不要重复注册");
        else
        {
            idCardMapper.addOrAdjustidCard2(idCard);
            return  Result.success("success");
        }

    };

    //删除
    public String deleteidCard2(int[]ids){
        idCardMapper.deleteidCard2(ids);
        return "success";
    }

    //    分页查询
    public List<idCard>selectByPage2(int index, int pageSize)
    {
        return idCardMapper.selectByPage2(index,pageSize);
    };

    //    数据总条目数
    public int selectTotalCount2(){
        return idCardMapper.selectTotalCount2();
    };

    //    分页条件查询
    public List<idCard>selectByPageWithCondition2(int index, int pageSize, idCard idCard){
        return idCardMapper.selectByPageWithCondition2(index,pageSize,idCard);
    };

    //    分页条件查询FORUSER
    public List<idCard>selectByPageWithConditionForUser2(int index, int pageSize, connect connect){
        return  idCardMapper.selectByPageWithConditionForUser2(index,pageSize,connect);
    };


    //    数据总条目数
    public int selectTotalCountWithCondition2(idCard idCard){
        return idCardMapper.selectTotalCountWithCondition2(idCard);
    };

    //    数据总条目数ForUser
    public int selectTotalCountWithConditionForUser2(connect connect){
        return idCardMapper.selectTotalCountWithConditionForUser2(connect);
    };

    //    修改数据
    public String updateidCard2(idCard idCard){
        idCardMapper.updateidCard2(idCard);
        return "success";
    };

    public List<idCard> selectByPageAllRight2(int index, int pageSize) {
        return idCardMapper.selectByPageAllRright2(index,pageSize);
    }

    public Integer selectTotalCountAllRight2() {
        return idCardMapper.selectTotalCountAllRight2();
    }

}
