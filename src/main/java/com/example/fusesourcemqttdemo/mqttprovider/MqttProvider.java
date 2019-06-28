package com.example.fusesourcemqttdemo.mqttprovider;

import com.alibaba.fastjson.JSONObject;
import com.example.fusesourcemqttdemo.entity.AccessctrInfo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;

/**
 * @program: fusesourcemqttdemo
 * @description:
 * @author: liuhanru
 * @create: 2019-05-22 11:15
 **/
@Configuration
public class MqttProvider {
    private static String urlTcp = "tcp://192.168.139.136:1883";
    private final static String CONNECTION_STRING = urlTcp;
    //private final static String CONNECTION_STRING = urlTcp;
    private final static boolean CLEAN_START = true;
    private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s
    public  static Topic[] topics = {
            new Topic("shenzhen", QoS.EXACTLY_ONCE),
            new Topic("tieyue", QoS.AT_LEAST_ONCE),
            new Topic("teamway", QoS.AT_MOST_ONCE)};
    public final  static long RECONNECTION_ATTEMPT_MAX=6;
    public final  static long RECONNECTION_DELAY=2000;

    public final static int SEND_BUFFER_SIZE=2*1024*1024;//发送最大缓冲为2M

    public final static   File f = new File("D:\\test\\teamway\\test.jpg");
    public static  byte[] byteArray = new byte[(int)f.length()];

   @Bean
   public void providerOfMqtt(){
        MQTT mqtt = new MQTT();
        try {
            //设置服务端的ip
            mqtt.setHost(CONNECTION_STRING);
            //连接前清空会话信息
            mqtt.setCleanSession(CLEAN_START);
            //设置重新连接的次数
            mqtt.setReconnectAttemptsMax(RECONNECTION_ATTEMPT_MAX);
            //设置重连的间隔时间
            mqtt.setReconnectDelay(RECONNECTION_DELAY);
            //设置心跳时间
            mqtt.setKeepAlive(KEEP_ALIVE);
            //设置缓冲的大小
            mqtt.setSendBufferSize(SEND_BUFFER_SIZE);

            //创建连接
            BlockingConnection connection = mqtt.blockingConnection();
            //开始连接
            connection.connect();
            try {
                int count=0;
                while(true){
                    count++;
                    //订阅的主题
                    String topic="teamway";
                    String topic1 = "tieyue";
                    String topic2 = "shenzhen";
                    //主题的内容
                    String message="hello "+count+"chinese people !";

                    //将消息以json字符串方式发送
                    AccessctrInfo accessctrInfo = new AccessctrInfo();
                    accessctrInfo.setDoorId(1);
                    accessctrInfo.setDoorName("研发部");
                    accessctrInfo.setUserName("lhr");
                    JSONObject json = (JSONObject) JSONObject.toJSON(accessctrInfo);
                    String accessctrInfoJson=json.toString();

                    //发送换成base64的图片信息
                   /* //File f = new File("D:\\test\\teamway\\test.jpg");     //change path of image according to you
                    FileInputStream fis = new FileInputStream(f);
                    //byte byteArray[] = new byte[(int)f.length()];
                    fis.read(byteArray);
                    String imageString = Base64.encodeBase64String(byteArray);*/


                    connection.publish(topic, accessctrInfoJson.getBytes(), QoS.AT_LEAST_ONCE, false);
                    //connection.publish(topic1, message.getBytes(), QoS.AT_LEAST_ONCE, false);
                    //connection.publish(topic2, "hello World!==============".getBytes(), QoS.AT_LEAST_ONCE, false);
                    System.out.println("MQTTServer Message  Count:"+count+"  Topic="+topic+"  Content :"+accessctrInfoJson);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   }
}