package com.ljw.crawlerdemo.controller;

import com.google.common.collect.Maps;
import com.ljw.crawlerdemo.constant.SysConstant;
import com.ljw.crawlerdemo.service.SpiderService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class SpiderController {


    @Autowired
    private SpiderService spiderService;

    private static final Logger logger = LoggerFactory.getLogger(SpiderController.class);

    public void spiderData() {
        logger.info("爬虫开始....");
        Date startDate = new Date();
        //创建固定数量的线程池提交任务，线程数量根据实际自定义
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //使用countDownLatch进行线程同步，使主线程等待线程池的所有任务结束，便于计时
        //此处注意和join()的区别，countDownLatch为了更加灵活的控制线程的互相等待
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for(int i = 1; i < 201; i += 2) {
            Map<String, String> params = Maps.newHashMap();
            params.put("keyword", "华为p20");
            params.put("enc", "utf-8");
            params.put("wc", "华为");
            params.put("page", i + "");
            //这里使用Java8中的lambda表达式替代匿名内部类
            executorService.submit(() -> {
                spiderService.spiderData(SysConstant.BASE_URL, params);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭线程池，不再接受新任务，等待所有的线程执行完提交的任务
        //注意shutdown()的用法，和shutdownNow()做区分
        executorService.shutdown();

        Date endDate = new Date();
        FastDateFormat fdf = FastDateFormat.getInstance(SysConstant.DEFAULT_DATE_FORMAT);
        logger.info("爬虫结束....");
        logger.info("[开始时间:" + fdf.format(startDate) + ",结束时间:" + fdf.format(endDate) + ",一共耗时:"
                + (endDate.getTime() - startDate.getTime()) + "ms]");
    }


//    public void ipData(){
//        {
//            logger.info("ip代理爬虫开始....");
//            Date startDate = new Date();
//            // 使用现线程池提交任务
//            ExecutorService executorService = Executors.newFixedThreadPool(5);
//            //引入countDownLatch进行线程同步，使主线程等待线程池的所有任务结束，便于计时
//            CountDownLatch countDownLatch = new CountDownLatch(100);
//            for(int i = 1; i < 200; i ++) {
//                Map<String, String> params = Maps.newHashMap();
//                params.put("page", i + "");
//                executorService.submit(() -> {
//                    spiderService.spiderData(SysConstant.IP_URL, params);
//                    countDownLatch.countDown();
//                });
//            }
//            try {
//                countDownLatch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            executorService.shutdown();
//            Date endDate = new Date();
//
//            FastDateFormat fdf = FastDateFormat.getInstance(SysConstant.DEFAULT_DATE_FORMAT);
//            logger.info("爬虫结束....");
//            logger.info("[开始时间:" + fdf.format(startDate) + ",结束时间:" + fdf.format(endDate) + ",耗时:"
//                    + (endDate.getTime() - startDate.getTime()) + "ms]");
//
//        }
//
//    }

}
