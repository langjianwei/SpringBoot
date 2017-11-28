/**
 * 
 */
package com.ljw.springboot_mybatis_redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郎建伟
 *
 */

@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String hello(){
		
		return "hello，郎建伟";
		
	}

}
