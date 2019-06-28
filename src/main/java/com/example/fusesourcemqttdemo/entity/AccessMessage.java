package com.example.fusesourcemqttdemo.entity;

import java.util.Objects;

/**
 * @program: acsproxy
 * @description:
 * @author: Zhao Hong Ning
 * @create: 2019-05-30 16:08
 **/
public class AccessMessage {
    private String Version="1.0";
    private String DeviceType;
    private String DataType;
    private Payload Payload;
    private GatewayInfo GatewayInfo;
    private HostInfo HostInfo;
    private ChannelInfo ChannelInfo;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public com.example.fusesourcemqttdemo.entity.Payload getPayload() {
        return Payload;
    }

    public void setPayload(com.example.fusesourcemqttdemo.entity.Payload payload) {
        Payload = payload;
    }

    public com.example.fusesourcemqttdemo.entity.GatewayInfo getGatewayInfo() {
        return GatewayInfo;
    }

    public void setGatewayInfo(com.example.fusesourcemqttdemo.entity.GatewayInfo gatewayInfo) {
        GatewayInfo = gatewayInfo;
    }

    public com.example.fusesourcemqttdemo.entity.HostInfo getHostInfo() {
        return HostInfo;
    }

    public void setHostInfo(com.example.fusesourcemqttdemo.entity.HostInfo hostInfo) {
        HostInfo = hostInfo;
    }

    public com.example.fusesourcemqttdemo.entity.ChannelInfo getChannelInfo() {
        return ChannelInfo;
    }

    public void setChannelInfo(com.example.fusesourcemqttdemo.entity.ChannelInfo channelInfo) {
        ChannelInfo = channelInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessMessage that = (AccessMessage) o;
        return Objects.equals(Version, that.Version) &&
                Objects.equals(DeviceType, that.DeviceType) &&
                Objects.equals(DataType, that.DataType) &&
                Objects.equals(Payload, that.Payload) &&
                Objects.equals(GatewayInfo, that.GatewayInfo) &&
                Objects.equals(HostInfo, that.HostInfo) &&
                Objects.equals(ChannelInfo, that.ChannelInfo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Version, DeviceType, DataType, Payload, GatewayInfo, HostInfo, ChannelInfo);
    }

    @Override
    public String toString() {
        return "AccessMessage{" +
                "Version='" + Version + '\'' +
                ", DeviceType='" + DeviceType + '\'' +
                ", DataType='" + DataType + '\'' +
                ", Payload=" + Payload +
                ", GatewayInfo=" + GatewayInfo +
                ", HostInfo=" + HostInfo +
                ", ChannelInfo=" + ChannelInfo +
                '}';
    }
}