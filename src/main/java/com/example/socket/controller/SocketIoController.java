package com.example.socket.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.socket.domain.Message;
import com.example.socket.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class SocketIoController {

    @Autowired
    private SocketIOServer socketServer;

    @Autowired
    private MessageRepository messageRepository;

     SocketIoController(SocketIOServer socketServer){
        this.socketServer=socketServer;
        this.socketServer.addConnectListener(onUserConnectWithSocket);
        this.socketServer.addDisconnectListener(onUserDisconnectWithSocket);
        this.socketServer.addEventListener("public", Message.class, onPublicMessage);
        this.socketServer.addEventListener("private", Message.class, onPrivateMessage);
        this.socketServer.addEventListener("getMessage", Void.class, getMessage);
    }


    public ConnectListener onUserConnectWithSocket = new ConnectListener() {
        @Override
        public void onConnect(SocketIOClient client) {
            log.info("Perform operation on user connect in controller");
        }
    };

public DisconnectListener onUserDisconnectWithSocket = new DisconnectListener() {
    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        log.info("Perform operation on user disconnect in controller");
    }
};

    public DataListener<Message> onPublicMessage = new DataListener<Message>() {
        @Override
        public void onData(SocketIOClient client, Message message, AckRequest acknowledge) throws Exception {

                socketServer.getBroadcastOperations().sendEvent("getPublicMessage", client, message);
                acknowledge.sendAckData("Message send to target user successfully");
        }
    };

    public DataListener<Message> onPrivateMessage = new DataListener<Message>() {
        @Override
        public void onData(SocketIOClient client, Message message, AckRequest acknowledge) throws Exception {
            socketServer.getBroadcastOperations().sendEvent("getPrivateMessage", client, message);
            acknowledge.sendAckData("Message send to target user successfully");
        }
    };

   public DataListener<Void> getMessage = new DataListener<Void>() {
       @Override
       public void onData(SocketIOClient socketIOClient, Void  string, AckRequest ackRequest) throws Exception {
           List<Message> messages = messageRepository.findAll();
//           socketServer.getBroadcastOperations().sendEvent("messages", socketIOClient, messages);
           ackRequest.sendAckData(messages);
       }
   };

}
