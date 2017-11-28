package com.ljw.springboot_mybatis_redis.dao;

import com.ljw.springboot_mybatis_redis.model.StudentInfo;
import com.ljw.springboot_mybatis_redis.model.StudentInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentInfoMapper {
    long countByExample(StudentInfoExample example);

    int deleteByExample(StudentInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    List<StudentInfo> selectByExample(StudentInfoExample example);

    StudentInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentInfo record, @Param("example") StudentInfoExample example);

    int updateByExample(@Param("record") StudentInfo record, @Param("example") StudentInfoExample example);

    int updateByPrimaryKeySelective(StudentInfo record);

    int updateByPrimaryKey(StudentInfo record);
}