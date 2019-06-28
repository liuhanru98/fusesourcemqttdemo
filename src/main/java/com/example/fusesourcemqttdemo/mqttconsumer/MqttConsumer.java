package com.example.fusesourcemqttdemo.mqttconsumer;

import com.alibaba.fastjson.JSONObject;
import com.example.fusesourcemqttdemo.entity.AccessctrInfo;
import com.example.fusesourcemqttdemo.kafkaprovider.MQClient;
import org.apache.tomcat.util.codec.binary.Base64;
import org.fusesource.mqtt.client.*;

import java.io.FileOutputStream;
import java.net.URISyntaxException;

import static com.example.fusesourcemqttdemo.mqttprovider.MqttProvider.byteArray;

/**
 * @program: fusesourcemqttdemo
 * @description:
 * @author: liuhanru
 * @create: 2019-05-22 11:15
 **/
public class MqttConsumer {
    //private final static String CONNECTION_STRING = "tcp://192.168.139.130:1883";
    private final static String CONNECTION_STRING = "tcp://192.168.0.134:1883";
    private final static boolean CLEAN_START = true;
    private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s
    private final static String CLIENT_ID = "publishService";
    public  static Topic[] topics = {
            new Topic("shenzhen", QoS.EXACTLY_ONCE),
            new Topic("tieyue", QoS.AT_LEAST_ONCE),
            new Topic("teamway", QoS.AT_MOST_ONCE)};
    public final  static long RECONNECTION_ATTEMPT_MAX=6;
    public final  static long RECONNECTION_DELAY=2000;

    public final static int SEND_BUFFER_SIZE=2*1024*1024;//发送最大缓冲为2M


    public static void main(String[] args)   {
        //创建MQTT对象
        MQTT mqtt = new MQTT();
        BlockingConnection connection=null;
        try {
            //设置mqtt broker的ip和端口
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


            //获取mqtt的连接对象BlockingConnection
            connection = mqtt.blockingConnection();
            //MQTT连接的创建
            connection.connect();
            //创建相关的MQTT 的主题列表
            Topic[] topics = {new Topic("teamway", QoS.AT_LEAST_ONCE)};
            //订阅相关的主题信息
            byte[] qoses = connection.subscribe(topics);

            int count=0;
            while(true){
                count++;
                //接收订阅的消息内容
                Message message = connection.receive();
                //获取订阅的消息内容
                byte[] payload = message.getPayload();

                //将收到的消息转成实体对象
                String json = new String(payload);
                AccessctrInfo accessctrInfo = JSONObject.parseObject(json, AccessctrInfo.class);

                //将收到的图片信息转换成图片
                /*FileOutputStream fos = new FileOutputStream("D:\\test\\teamway\\testChange"+count+".jpg"); //change path of image according to you
                byteArray = Base64.decodeBase64(new String(payload));
                fos.write(byteArray);*/

                // 往kafka服务器发送消息
                MQClient mqClient = MQClient.instance();
                mqClient.init("192.168.139.130", 9092);
                //mqClient.init("192.168.0.134", 9092);
                mqClient.subscribe("mqttkafka");
                mqClient.send("mqttkafka", "1", json);

               // process the message then:
                System.out.println("MQTTClient Message  Count:"+count+"  Topic="+message.getTopic()+" Content :"+"  DoorId:"+accessctrInfo.getDoorId()+"  DoorName:"+accessctrInfo.getDoorName()+"  UserName:"+accessctrInfo.getUserName());
                //签收消息的回执
                message.ack();

            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                connection.disconnect();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}