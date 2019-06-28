package com.example.fusesourcemqttdemo.kafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @program: fusesourcemqttdemo
 * @description:
 * @author: liuhanru
 * @create: 2019-05-27 16:40
 **/
public class KafkaConsumerTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.139.136:9092");// 必须指定
        props.put("group.id", "groupIdTest"); // 必须指定
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "latest");// 从最早的消息开始读取
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");// 必须指定
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");// 必须指定

        //创建consumer实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //订阅topic
        consumer.subscribe(Arrays.asList("mqttkafka"));

       while (true){
           ConsumerRecords<String, String> records = consumer.poll(1000);
           for (ConsumerRecord<String, String> record : records) {
               System.out.println("------------------收到解析后的数据----------------------");
               System.out.println("---------------------------" + record.value() + "-------------------------");

               //String key = record.key();
               //String reply = record.value();
           }
       }
    }
}