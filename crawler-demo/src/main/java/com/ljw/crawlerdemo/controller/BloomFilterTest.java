package com.ljw.crawlerdemo.controller;

import com.ljw.crawlerdemo.dao.ProductInfoDao;
import com.ljw.crawlerdemo.utils.LocalBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BloomFilterTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    LocalBloomFilter localBloomFilter=new LocalBloomFilter();


    public void task(){
        //去数据库查询从京东爬回来的所有商品信息URL
        List<Map<String,Object>> list=productInfoDao.selectAll();
        System.out.println("数据库查出来的所有URL数据总条数："+list.size());

        Map<String,Object> map=new HashMap<String,Object>();
        //遍历所有的URL
        for (int i = 0; i <list.size() ; i++) {
            String product_id=list.get(i).get("product_id").toString();
            //去重判断：如果过滤器里面不存在，就添加进去，否则判断下一条
            if (this.localBloomFilter.contains(product_id)) {
                continue;
            }
            else
                this.localBloomFilter.addValue(product_id);
                map.put(product_id, product_id);
        }
        System.out.println("URL去重过滤之后的总条数："+map.size());
    }


}
