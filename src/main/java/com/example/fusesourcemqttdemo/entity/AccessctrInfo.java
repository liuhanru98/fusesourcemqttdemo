package com.example.fusesourcemqttdemo.entity;

import java.util.Objects;

/**
 * @program: fusesourcemqttdemo
 * @description:
 * @author: liuhanru
 * @create: 2019-05-22 11:30
 **/
public class AccessctrInfo {
    private Integer doorId;
    private String doorName;
    private String userName;

    public Integer getDoorId() {
        return doorId;
    }

    public void setDoorId(Integer doorId) {
        this.doorId = doorId;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessctrInfo that = (AccessctrInfo) o;
        return Objects.equals(doorId, that.doorId) &&
                Objects.equals(doorName, that.doorName) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(doorId, doorName, userName);
    }

    @Override
    public String toString() {
        return "AccessctrInfo{" +
                "doorId=" + doorId +
                ", doorName='" + doorName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}