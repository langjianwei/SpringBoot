package com.ljw.crawlerdemo.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ljw.crawlerdemo.common.HttpClientUtils;
import com.ljw.crawlerdemo.dao.ProductInfoDao;
import com.ljw.crawlerdemo.entity.ProductInfo;
import com.ljw.crawlerdemo.utils.LocalBloomFilter;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpiderService {

    private static Logger logger = LoggerFactory.getLogger(SpiderService.class);
    @Autowired
    private ProductInfoDao productInfoDao;

    LocalBloomFilter localBloomFilter = new LocalBloomFilter();

    private static String HTTPS_PROTOCOL = "https:";

    public void spiderData(String url, Map<String, String> params) {
        String html = HttpClientUtils.sendGet(url, null, params);
        if(!StringUtils.isBlank(html)) {
            List<ProductInfo> productInfo =parseHtml(html);
            productInfoDao.batchSave(productInfo);
        }
    }
    /**
     * 解析html
     * @param html
     */
    private List<ProductInfo> parseHtml(String html) {
        //商品集合
        List<ProductInfo> productInfos = Lists.newArrayList();
        /**
         * 获取dom并解析
         */
        Document document = Jsoup.parse(html);
        Elements elements = document.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        for(Element element : elements) {
            //商品ID
            String productId = element.attr("data-sku");
            //商品名称
            String productName = element.select("div[class=p-name p-name-type-2]").select("em").text();
            //商品价格
            String productPrice = element.select("div[class=p-price]").select("strong").select("i").text();
            //商品详情URL
            String imgUrl = HTTPS_PROTOCOL + element.select("div[class=p-img]").select("a").attr("href");
            //URL去重
            if (this.localBloomFilter.contains(imgUrl))
                continue;
            else
                this.localBloomFilter.addValue(imgUrl);
            ProductInfo productInfo = new ProductInfo(productId, productName, imgUrl, productPrice);
            productInfos.add(productInfo);
            String jsonStr = JSON.toJSONString(productInfos);
            logger.info("成功爬取【" + productName + "】的基本信息 ");
            logger.info(jsonStr);
        }
        return productInfos;
    }
}
