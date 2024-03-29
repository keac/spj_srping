package com.spj.demo.pojo;

public class Teach {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teach.teach_id
     *
     * @mbggenerated
     */
    private String teachId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teach.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teach.sex
     *
     * @mbggenerated
     */
    private Integer sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teach.branch_id
     *
     * @mbggenerated
     */
    private String branchId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teach.say
     *
     * @mbggenerated
     */
    private String say;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teach.teach_id
     *
     * @return the value of teach.teach_id
     *
     * @mbggenerated
     */
    public String getTeachId() {
        return teachId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teach.teach_id
     *
     * @param teachId the value for teach.teach_id
     *
     * @mbggenerated
     */
    public void setTeachId(String teachId) {
        this.teachId = teachId == null ? null : teachId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teach.name
     *
     * @return the value of teach.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teach.name
     *
     * @param name the value for teach.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teach.sex
     *
     * @return the value of teach.sex
     *
     * @mbggenerated
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teach.sex
     *
     * @param sex the value for teach.sex
     *
     * @mbggenerated
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teach.branch_id
     *
     * @return the value of teach.branch_id
     *
     * @mbggenerated
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teach.branch_id
     *
     * @param branchId the value for teach.branch_id
     *
     * @mbggenerated
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teach.say
     *
     * @return the value of teach.say
     *
     * @mbggenerated
     */
    public String getSay() {
        return say;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teach.say
     *
     * @param say the value for teach.say
     *
     * @mbggenerated
     */
    public void setSay(String say) {
        this.say = say == null ? null : say.trim();
    }
}