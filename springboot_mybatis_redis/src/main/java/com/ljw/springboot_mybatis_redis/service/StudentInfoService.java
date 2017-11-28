/**
 * 
 */
package com.ljw.springboot_mybatis_redis.service;

import com.ljw.springboot_mybatis_redis.model.StudentInfo;

/**
 * @author 郎建伟
 *
 */
public interface StudentInfoService {
	
	/**
	 * 根据id查找学生
	 * @param id
	 * @return
	 */
	StudentInfo findStudentInfoById(Integer id);
	
	/**
	 * 新增/保存学生信息
	 * @param studentInfo
	 * @return
	 */
	Integer saveStudentInfo(StudentInfo studentInfo);
	
	/**
	 *更新学生信息
	 * @param studentInfo
	 * @return
	 */
	Integer updaateStudentInfo(StudentInfo studentInfo);

}
