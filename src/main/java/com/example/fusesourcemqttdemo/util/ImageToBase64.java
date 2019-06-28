package com.example.fusesourcemqttdemo.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @program: fusesourcemqttdemo
 * @description:
 * @author: liuhanru
 * @create: 2019-05-22 14:21
 **/
public class ImageToBase64 {
    public static void main(String...s) throws Exception{

        //encode image to Base64 String
        File f = new File("D:\\test\\teamway\\test.jpg");     //change path of image according to you
        FileInputStream fis = new FileInputStream(f);
        byte byteArray[] = new byte[(int)f.length()];
        fis.read(byteArray);
        String imageString = Base64.encodeBase64String(byteArray);

        //decode Base64 String to image
        FileOutputStream fos = new FileOutputStream("D:\\test\\teamway\\testChange.jpg"); //change path of image according to you
        byteArray = Base64.decodeBase64(imageString);
        fos.write(byteArray);

        fis.close();
        fos.close();
    }
}