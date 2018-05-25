package com.ljw.crawlerdemo.dao;

import com.ljw.crawlerdemo.entity.ProductInfo;

import java.util.List;
import java.util.Map;

public interface ProductInfoDao {
    /**
     * 批量保存商品信息
     * @param productInfoList
     */
    void batchSave(List<ProductInfo> productInfoList);


    /**
     * 批量查询商品信息
     * @param
     */
    List<Map<String,Object>> selectAll();

}
