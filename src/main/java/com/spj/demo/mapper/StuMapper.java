package com.spj.demo.mapper;

import com.spj.demo.pojo.Stu;

public interface StuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String stuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated
     */
    int insert(Stu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated
     */
    int insertSelective(Stu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated
     */
    Stu selectByPrimaryKey(String stuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Stu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Stu record);
}