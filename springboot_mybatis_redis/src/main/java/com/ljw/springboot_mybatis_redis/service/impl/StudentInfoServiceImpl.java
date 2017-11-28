/**
 * 
 */
package com.ljw.springboot_mybatis_redis.service.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.ljw.springboot_mybatis_redis.dao.StudentInfoMapper;
import com.ljw.springboot_mybatis_redis.model.StudentInfo;
import com.ljw.springboot_mybatis_redis.service.StudentInfoService;

/**
 * @author 郎建伟
 *
 */
@Service
public class StudentInfoServiceImpl implements StudentInfoService{
	
	private static final Logger LOGGER=LoggerFactory.getLogger(StudentInfoServiceImpl.class);
	
	@Autowired
	private StudentInfoMapper studentInfoMapper;
	
	@Autowired
	private RedisTemplate redisTemplate;
	@Override
	public StudentInfo findStudentInfoById(Integer id) {
		 // 从缓存中获取学生信息
        String key = "StudentInfo_" + id;
        ValueOperations<String, StudentInfo> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            StudentInfo studentInfo = operations.get(key);

            LOGGER.info("根据id查找学生信息 : 从缓存中获取了学生信息 >> " + studentInfo.toString());
            return studentInfo;
        }

        // 从 数据库 中获取学生信息
        StudentInfo studentInfo = studentInfoMapper.selectByPrimaryKey(id);

        // 插入缓存
        operations.set(key, studentInfo, 10, TimeUnit.SECONDS);
        LOGGER.info("根据id查找学生信息 : 学生信息插入缓存 >> " + studentInfo.toString());
		
        return studentInfo;
		
	}

	/* (non-Javadoc)
	 * @see com.ljw.springboot_mybatis_redis.service.StudentInfoService#findStudentInfoById(java.lang.Integer)
	 */

	/* (non-Javadoc)
	 * @see com.ljw.springboot_mybatis_redis.service.StudentInfoService#saveStudentInfo(com.ljw.springboot_mybatis_redis.model.StudentInfo)
	 */
	@Override
	public Integer saveStudentInfo(StudentInfo studentInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ljw.springboot_mybatis_redis.service.StudentInfoService#updaateStudentInfo(com.ljw.springboot_mybatis_redis.model.StudentInfo)
	 */
	@Override
	public Integer updaateStudentInfo(StudentInfo studentInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
