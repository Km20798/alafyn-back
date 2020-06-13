package com.karim.controller;


import com.karim.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalTime;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MessageChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/mes")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        System.out.println("send message " + chatMessage.getContent() + chatMessage.getSender());
        LocalTime time = LocalTime.now();

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setContent(chatMessage.getContent());
        chatMessage1.setSender(chatMessage.getSender());
        chatMessage1.setTime(time);
        return chatMessage1;
    }


}