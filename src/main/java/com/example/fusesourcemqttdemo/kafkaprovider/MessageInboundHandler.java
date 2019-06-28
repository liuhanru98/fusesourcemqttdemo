
package com.example.fusesourcemqttdemo.kafkaprovider;

public interface MessageInboundHandler {
    void messageReceived(String message);
}
