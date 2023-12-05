package com.huzd.huji.Service;

import com.huzd.huji.mapper.hujiMapper;
import com.huzd.huji.pojo.connect;
import com.huzd.huji.pojo.huji;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class hujiService {
    @Autowired
    private hujiMapper hujiMapper;

    public List<huji> selectAll(){
        return hujiMapper.selectAll();
    };

    public String addHuji( huji huji){
        hujiMapper.addHuji(huji);
        return "success";
    };

    public String updateHuji2( huji huji){
        hujiMapper.updateHuji2(huji);
        return "success";
    }

    public String deleteByIds( int []ids){
        hujiMapper.deleteByIds(ids);
        return "success";
    }

    public List<huji> selectByPage( int currentPage, int pageSize){
        return hujiMapper.selectByPage(currentPage, pageSize);


    };

    public List<huji> selectByPageWithCondition( int currentPage, int pageSize,@RequestBody huji huji){


        return hujiMapper.selectByPageWithCondition(currentPage, pageSize,huji);

    };

    public int selectTotalCount(){
        return hujiMapper.selectTotalCount();
    }

    public int selectTotalCountWithCondition(huji huji){
        return hujiMapper.selectTotalCountWithCondition(huji);
    }

    public huji selectByNameAndId(huji huji){return hujiMapper.selectByNameAndId(huji);}

}
