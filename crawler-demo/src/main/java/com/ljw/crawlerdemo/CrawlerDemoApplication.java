package com.ljw.crawlerdemo;

import com.ljw.crawlerdemo.controller.BloomFilterTest;
import com.ljw.crawlerdemo.controller.SpiderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlerDemoApplication {

    @Autowired
    private SpiderController spiderController;

    @Autowired
    private BloomFilterTest bloomFilterTest;



    public static void main(String[] args) {
        SpringApplication.run(CrawlerDemoApplication.class, args);
    }







//    @PostConstruct
//    public void task() {
//        spiderController.spiderData();
//    }


    //测试布隆过滤器
//    @PostConstruct
//    public void task(){
//        bloomFilterTest.task();
//    }
}
