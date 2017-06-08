package com.main.example.data.mybatisplus.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.main.example.data.mybatisplus.dao.MyBatisExampleMapper;
import com.main.example.data.mybatisplus.entity.MyBatisExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xsooy-pc on 2017/5/30.
 */
@Service
public class MyBatisExampleService {
    @Autowired
    MyBatisExampleMapper mapper;

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public MyBatisExample selectById(long id){
        MyBatisExample example2=mapper.selectById(id);
        return example2;
    }

    /**
     * 新增
     * @param example
     */
    public void insert(MyBatisExample example){
        mapper.insert(example);
    }

    /**
     * 修改
     * @return
     */
    public void update(MyBatisExample example){
        mapper.updateById(example);
    }

    /**
     * 查询所有
     * @return
     */
    public List<MyBatisExample> findAll(){
      return  mapper.selectList(new EntityWrapper<MyBatisExample>());
    }
    /**
     *条件查询集合
     */
    public List<MyBatisExample> findList(){
        return mapper.selectList(new EntityWrapper<MyBatisExample>().gt("age",18));
    }
    /**
     * 删除
     */
    public void delete(MyBatisExample example){
        mapper.deleteById(example.getId());
    }
    /**
     * 分页查询
     */
    public List<MyBatisExample> findPage(){
        return mapper.selectPage(new Page<MyBatisExample>(1,10),new EntityWrapper<MyBatisExample>());
    }
}
