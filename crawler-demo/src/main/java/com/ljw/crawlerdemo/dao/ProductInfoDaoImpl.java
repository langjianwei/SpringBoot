package com.ljw.crawlerdemo.dao;

import com.ljw.crawlerdemo.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductInfoDaoImpl implements ProductInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void batchSave(List<ProductInfo> productInfoList) {
        String sql="insert into product("+"product_id,"+"product_name,"+"img_url,"+"product_price) values(?,?,?,?)";

        for (ProductInfo productInfo:productInfoList) {
            jdbcTemplate.update(sql,productInfo.getProductId(),productInfo.getProductName(),productInfo.getImgUrl(),productInfo.getProductPrice());

        }
    }


    @Override
    public List<Map<String,Object>> selectAll() {
        String sql="select product_id from product";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        return list;
    }
}
