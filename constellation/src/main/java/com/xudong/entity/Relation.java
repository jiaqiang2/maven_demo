package com.xudong.entity;

public class Relation {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_iot_data_point_relation.pid
     *
     * @mbggenerated Fri Dec 21 14:36:26 CST 2018
     */
    private String pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_iot_data_point_relation.deviceId
     *
     * @mbggenerated Fri Dec 21 14:36:26 CST 2018
     */
    private String deviceid;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table g_iot_data_point_relation
     *
     * @mbggenerated Fri Dec 21 14:36:26 CST 2018
     */
    public Relation(String pid, String deviceid) {
        this.pid = pid;
        this.deviceid = deviceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table g_iot_data_point_relation
     *
     * @mbggenerated Fri Dec 21 14:36:26 CST 2018
     */
    public Relation() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_iot_data_point_relation.pid
     *
     * @return the value of g_iot_data_point_relation.pid
     *
     * @mbggenerated Fri Dec 21 14:36:26 CST 2018
     */
    public String getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_iot_data_point_relation.pid
     *
     * @param pid the value for g_iot_data_point_relation.pid
     *
     * @mbggenerated Fri Dec 21 14:36:26 CST 2018
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_iot_data_point_relation.deviceId
     *
     * @return the value of g_iot_data_point_relation.deviceId
     *
     * @mbggenerated Fri Dec 21 14:36:26 CST 2018
     */
    public String getDeviceid() {
        return deviceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_iot_data_point_relation.deviceId
     *
     * @param deviceid the value for g_iot_data_point_relation.deviceId
     *
     * @mbggenerated Fri Dec 21 14:36:26 CST 2018
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table g_iot_data_point_relation
     *
     * @mbggenerated Fri Dec 21 14:36:26 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pid=").append(pid);
        sb.append(", deviceid=").append(deviceid);
        sb.append("]");
        return sb.toString();
    }
}