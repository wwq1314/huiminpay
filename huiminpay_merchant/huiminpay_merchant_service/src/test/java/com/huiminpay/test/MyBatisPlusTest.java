package com.huiminpay.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huiminpay.bean.Merchant;
import com.huiminpay.mapper.IMerchantMapper;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    IMerchantMapper merchantMapper;

    /**
     * mybatis-plus使用默认的curd的时候，主键id如果是自增的，那么默认使用的是auto类型【也就是会自动回显id值】
     * mybatis中进行添加数据时，入股哦没有配置主键【select id】，主键id是不会回显的
     */
    @Test
    public void insertTest(){
        Merchant merchant = new Merchant();
        merchant.setMerchantName("plus test");
        int insert = merchantMapper.insert(merchant);
        System.out.println("添加结果："+insert);
        System.out.println("merchant:"+merchant);
    }


    @Test
    public void insertTest2(){
        Merchant merchant = new Merchant();
        merchant.setId(123L);
        merchant.setMerchantName("手动设置idplus test");
        int insert = merchantMapper.insert(merchant);
        System.out.println("添加结果："+insert);
        System.out.println("merchant:"+merchant);
    }


    @Test
    public void queryById(){
        Merchant merchant = merchantMapper.selectById(1);
        System.out.println(merchant);
    }


    @Test
    public void queryByName(){
        QueryWrapper<Merchant> queryWrapper = new QueryWrapper<>();
        //参数一：要查询的列，参数二：对应列要指定的值
        queryWrapper.eq("merchant_name","小菜集团");
        List<Merchant> merchants = merchantMapper.selectList(queryWrapper);
        System.out.println(merchants);
    }


    @Test
    public void pageQuery(){
        Page<Merchant> merchantPage = new Page<>(2, 5);
        //参数一：指定分页信息    参数二：指定搜索的条件
        IPage<Merchant> merchantIPage = merchantMapper.selectPage(merchantPage, null);
        List<Merchant> records = merchantIPage.getRecords();
        for (Merchant merchant : records){
            System.out.println(merchant);
        }
    }
}
