/**
 * 
 */
package com.ljw.springboot_mybatis_redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ljw.springboot_mybatis_redis.model.StudentInfo;
import com.ljw.springboot_mybatis_redis.service.StudentInfoService;

/**
 * @author 郎建伟
 *
 */
@RestController
public class StudentInfoController {
	
	@Autowired
	private StudentInfoService studentInfoService;
	
	 @RequestMapping(value = "studentInfo/{id}", method = RequestMethod.GET)
	 public StudentInfo findOneCity(@PathVariable("id") Integer id) {
	      return studentInfoService.findStudentInfoById(id);
	 }

}
