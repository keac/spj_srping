package com.spj.demo.mapper;

import com.spj.demo.pojo.PTest;

public interface PTestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p
     *
     * @mbggenerated
     */
    int insert(PTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p
     *
     * @mbggenerated
     */
    int insertSelective(PTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p
     *
     * @mbggenerated
     */
    PTest selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PTest record);
}