package com.spj.demo.pojo;

public class Stu {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stu.stu_id
     *
     * @mbggenerated
     */
    private String stuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stu.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stu.sex
     *
     * @mbggenerated
     */
    private Integer sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stu.class_id
     *
     * @mbggenerated
     */
    private String classId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stu.stu_id
     *
     * @return the value of stu.stu_id
     *
     * @mbggenerated
     */
    public String getStuId() {
        return stuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stu.stu_id
     *
     * @param stuId the value for stu.stu_id
     *
     * @mbggenerated
     */
    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stu.name
     *
     * @return the value of stu.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stu.name
     *
     * @param name the value for stu.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stu.sex
     *
     * @return the value of stu.sex
     *
     * @mbggenerated
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stu.sex
     *
     * @param sex the value for stu.sex
     *
     * @mbggenerated
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stu.class_id
     *
     * @return the value of stu.class_id
     *
     * @mbggenerated
     */
    public String getClassId() {
        return classId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stu.class_id
     *
     * @param classId the value for stu.class_id
     *
     * @mbggenerated
     */
    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }
}